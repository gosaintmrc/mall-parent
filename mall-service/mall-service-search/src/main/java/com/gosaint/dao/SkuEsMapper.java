package com.gosaint.dao;

import com.gosaint.search.domain.SkuInfo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 20:56 2019/12/22
 * @Modified By:
 */
public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo,Long> {
}
