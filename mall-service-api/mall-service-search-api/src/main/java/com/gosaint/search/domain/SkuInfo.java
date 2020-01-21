package com.gosaint.search.domain;



import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 20:08 2019/12/22
 * @Modified By:
 */
@Document(indexName = "skuinfo", type = "docs")
public class SkuInfo {

    @Id
    /*商品id*/
    private Long id;
    /**
     * type = FieldType.Text 支持分词
     * index = true 添加数据的时候是否分词
     *
     *
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart",index = true)
    private String name;

    @Field(type = FieldType.Double)
    private Integer price;

    /** 库存数量*/
    private Integer num;

    /** 商品图片*/
    private String image;

    /** 商品状态 1-正常，2-下架，3-删除*/
    private String status;
    /** 创建时间*/
    private Date createTime;

    private Date updateTime;

    private String isDefault;

    private Long spuId;

    private Integer categoryId;
    /**
     * type = FieldType.Keyword 不分词
     */
    @Field(type = FieldType.Keyword)
    private String categoryName;

    @Field(type = FieldType.Keyword)
    private String brandName;

    private String spec;
    /** 规格参数*/
    private Map<String,Object> specMap;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(final Integer price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(final Integer num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(final String isDefault) {
        this.isDefault = isDefault;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(final Long spuId) {
        this.spuId = spuId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(final Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(final String brandName) {
        this.brandName = brandName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(final String spec) {
        this.spec = spec;
    }

    public Map<String, Object> getSpecMap() {
        return specMap;
    }

    public void setSpecMap(final Map<String, Object> specMap) {
        this.specMap = specMap;
    }
}
