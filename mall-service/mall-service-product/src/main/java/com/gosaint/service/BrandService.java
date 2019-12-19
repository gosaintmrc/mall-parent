package com.gosaint.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
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

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(int page,int size);

    /***
     * 多条件分页查询
     * @param brand
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(Brand brand, int page, int size);

    /**
     * 根据商品分类id查询商品品牌
     * @param categoryId 商品分类id
     * @return
     */
    List<Brand> findByCategory(Integer categoryId);



}
