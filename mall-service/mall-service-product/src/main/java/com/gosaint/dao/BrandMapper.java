package com.gosaint.dao;

import java.util.List;

import com.gosaint.product.domain.Brand;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 0:00 2019/12/17
 * @Modified By:
 */
public interface BrandMapper extends Mapper<Brand> {
    /**
     * 根据分类id查询商品品牌
     * @param categoryId
     * @return
     */
    @Select("SELECT brand.* FROM tb_brand brand,tb_category_brand tcb WHERE brand.id=tcb.brand_id AND tcb.category_id=#{categoryId}")
    List<Brand> findByCategoryId(Integer categoryId);
}
