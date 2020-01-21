package com.gosaint.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.gosaint.dao.SkuEsMapper;
import com.gosaint.entity.Result;
import com.gosaint.product.domain.Sku;
import com.gosaint.product.feign.SkuFeign;
import com.gosaint.search.domain.SkuInfo;
import com.gosaint.service.SkuService;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 20:54 2019/12/22
 * @Modified By:
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuEsMapper skuEsMapper;

    @Resource
    private SkuFeign skuFeign;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 导入索引库
     */
    @Override
    public void imports() {
        Result<List<Sku>> skuList = skuFeign.findAll();
        List<SkuInfo> skuInfoList = JSON.parseArray(JSON.toJSONString(skuList.getData()), SkuInfo.class);
        for (SkuInfo skuInfo : skuInfoList) {
            //{'颜色': '粉色', '版本': '6GB+128GB'}
            Map<String, Object> specMap = JSON.parseObject(skuInfo.getSpec(), Map.class);
            /**
             * 如果要动态的生成一个域，只需要将该域存入到一个Map<String,Object>对象即可。
             * 该Map<String,Object>的key会生成一个域。域的名字为该Map的key
             */
            skuInfo.setSpecMap(specMap);
        }
        skuEsMapper.saveAll(skuInfoList);
    }

    /**
     * 多条件搜索
     *
     * @param search
     * @return
     */
    @Override
    public Map<String, Object> search(final Map<String, String> search) {
        /**
         * 构建查询条件
         */
        NativeSearchQueryBuilder nativeSearchQueryBuilder = buildBasicQuery(search);

        /**
         * SearchQuery 搜索条件,
         * Class<T> 返回结果
         * 集合搜索
         */
        Map<String, Object> res = searchList(nativeSearchQueryBuilder);
        List<String> categoryList = searchCategoryList(nativeSearchQueryBuilder);
        List<String> searchBrandList = searchBrandList(nativeSearchQueryBuilder);
        res.put("categoryList", categoryList);
        res.put("brandList", searchBrandList);
        return res;
    }

    private NativeSearchQueryBuilder buildBasicQuery(final Map<String, String> search) {
        /**
         * NativeSearchQueryBuilder构建对象
         */
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //根据关键词搜索
        if (search != null && search.size() > 0) {
            String keywords = search.get("keywords");
            if (!StringUtils.isEmpty(keywords)) {
                boolQueryBuilder.must(QueryBuilders.queryStringQuery(keywords).field("name"));
            }
            if (!StringUtils.isEmpty(search.get("category"))) {
                boolQueryBuilder.must(QueryBuilders.termQuery("categoryName", search.get("category")));
            }

            if (!StringUtils.isEmpty(search.get("brand"))) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandName", search.get("brand")));
            }

            for (Map.Entry<String, String> entry : search.entrySet()) {
                String key = entry.getKey();
                if (key.startsWith("spec_")) {
                    String value = entry.getValue();
                    boolQueryBuilder.must(QueryBuilders.termQuery("specMap." + key.substring(5), value));
                }
            }
            /**
             * 价格帅选
             */
            String price = search.get("price");
            if (!StringUtils.isEmpty(price)) {
                price = price.replace("元", "").replace("以上", "");
                String[] prices = price.split("-");
                if (prices != null && prices.length > 0) {
                    boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gt(Integer.parseInt(prices[0])));
                    if (prices.length == 2) {
                        boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gt(Integer.parseInt(prices[1])));
                    }
                }
            }
            sortSearch(search, nativeSearchQueryBuilder);
            pageSearch(search, nativeSearchQueryBuilder);

            nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        }
        return nativeSearchQueryBuilder;
    }

    /**
     * 分页实现
     *
     * @param search
     * @param nativeSearchQueryBuilder
     */
    private void pageSearch(final Map<String, String> search, final NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        Integer pageNum = convertPage(search);
        Integer size = 5;
        nativeSearchQueryBuilder.withPageable(PageRequest.of(pageNum - 1, size));
    }

    /**
     * 排序实现
     *
     * @param search
     * @param nativeSearchQueryBuilder
     */
    private void sortSearch(final Map<String, String> search, final NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        String field = search.get("sortField");
        String rule = search.get("sortRule");
        if (!StringUtils.isEmpty(field) && !StringUtils.isEmpty(rule)) {
            nativeSearchQueryBuilder.withSort(new FieldSortBuilder(field).order(SortOrder.valueOf(rule)));
        }
    }

    /**
     * 分页参数接收
     *
     * @param map
     * @return
     */
    private Integer convertPage(Map<String, String> map) {
        if (map != null) {
            String pageNum = map.get("pageNum");
            return Integer.parseInt(pageNum);
        }
        return 1;
    }

    private Map<String, Object> searchList(final NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        highSearch(nativeSearchQueryBuilder);
        AggregatedPage<SkuInfo> page = elasticsearchTemplate
                .queryForPage(nativeSearchQueryBuilder.build(), SkuInfo.class, new HighSearchMapper());

        /**
         * 总记录数
         * 总页数
         * 查询结果集合
         */
        long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<SkuInfo> content = page.getContent();
        Map<String, Object> res = new HashMap<>();
        res.put("rows", content);
        res.put("total", totalElements);
        res.put("totalPages", totalPages);
        return res;
    }

    /**
     * 高亮搜索配置
     *
     * @param nativeSearchQueryBuilder
     */
    private void highSearch(final NativeSearchQueryBuilder nativeSearchQueryBuilder) {

        //设置高亮域
        HighlightBuilder.Field field = new HighlightBuilder.Field("name");
        //前缀
        field.preTags("<em style=\"color:red;\">");
        field.postTags("</em>");
        //设置碎片长度
        field.fragmentOffset(100);
        nativeSearchQueryBuilder.withHighlightFields();
    }

    /**
     * 分类分组查询
     *
     * @param nativeSearchQueryBuilder
     * @return
     */
    private List<String> searchCategoryList(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuCategory").field("categoryName"));
        AggregatedPage<SkuInfo> ap = elasticsearchTemplate
                .queryForPage(nativeSearchQueryBuilder.build(), SkuInfo.class);
        StringTerms skuCategory = ap.getAggregations().get("skuCategory");
        List<String> categoriList = new ArrayList<>();
        for (StringTerms.Bucket bucket : skuCategory.getBuckets()) {
            String catagoryName = bucket.getKeyAsString();
            categoriList.add(catagoryName);
        }
        return categoriList;
    }

    /**
     * 品牌分组查询
     *
     * @param nativeSearchQueryBuilder
     * @return
     */
    private List<String> searchBrandList(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuBrand").field("brandName"));
        AggregatedPage<SkuInfo> ap = elasticsearchTemplate
                .queryForPage(nativeSearchQueryBuilder.build(), SkuInfo.class);
        StringTerms skuCategory = ap.getAggregations().get("skuBrand");
        List<String> brandList = new ArrayList<>();
        for (StringTerms.Bucket bucket : skuCategory.getBuckets()) {
            String brandName = bucket.getKeyAsString();
            brandList.add(brandName);
        }
        return brandList;
    }

    class HighSearchMapper implements SearchResultMapper {

        @Override public <T> AggregatedPage<T> mapResults(
                final SearchResponse searchResponse, final Class<T> aClass, final Pageable pageable) {
            //1 执行查询，获取所有数据---->结果集【高亮数据|非高亮数据】

            List<T> list=new ArrayList<>();
            for (SearchHit hit:searchResponse.getHits()) {
                //分析结果集，获取【非高亮数据】
                SkuInfo skuInfo = JSON.parseObject(hit.getSourceAsString(), SkuInfo.class);
                //获取高亮数据
                HighlightField highlightField = hit.getHighlightFields().get("name");
                if(highlightField!=null && highlightField.getFragments()!=null){
                    Text[] texts = highlightField.getFragments();
                    StringBuffer sb=new StringBuffer(60);
                    for (Text t:texts){
                        sb.append(t.toString());
                    }
                    //高亮数据替换非高亮数据
                    skuInfo.setName(sb.toString());
                }
                list.add((T) skuInfo);
            }
            return new AggregatedPageImpl<T>(list,pageable,searchResponse.getHits().getTotalHits());
        }

        @Override public <T> T mapSearchHit(final SearchHit searchHit, final Class<T> aClass) {
            return null;
        }
    }
}
