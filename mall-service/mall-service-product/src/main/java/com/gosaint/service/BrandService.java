package com.gosaint.service;

import java.util.List;

import com.gosaint.product.domain.Brand;

/**
 * @Authgor: gosaint
 * @Description:
 * @Date Created in 0:00 2019/12/17
 * @Modified By:
 */
public interface BrandService {
    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();

    /**
     * 多条件搜索
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /**
     * 添加品牌
     * @param brand
     */
    void add(Brand brand);

    /**
     * 根据id修改品牌
     * @param brand
     */
    void update(Brand brand);

    /**
     * 删除品牌
     * @param id
     */
    void delete(Integer id);



}
