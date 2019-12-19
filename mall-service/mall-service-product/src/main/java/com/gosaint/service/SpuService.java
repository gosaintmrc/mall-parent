package com.gosaint.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.gosaint.product.domain.Brand;
import com.gosaint.product.domain.Products;
import com.gosaint.product.domain.Spu;

/**
 * @Author: gosaint
 * @Description: 商品信息详情
 * @Date Created in 21:40 2019/12/19
 * @Modified By:
 */
public interface SpuService {

    /**
     * 添加商品信息
     * @param products
     */
    void saveGoods(Products products);

    /**
     * 根据spu id查询Products
     * @param id
     * @return
     */
    Products findGoodsById(Long id);

    /**
     * 商品审核
     * @param id spuId
     */
    void audit(Long id);

    /***
     * 下架商品
     * @param id
     */
    void pull(Long id);

    /**
     * 商品上架
     * @param id
     */
    void put(Long id);

    /**
     * 商品批量上架
     * @param ids
     */
    void batchPut(Long[] ids);


    /***
     * 多条件分页查询 商品分页
     * @param searchMap 多条件查询集合
     * @param page
     * @param size
     * @return
     */
    Page<Spu> findPage(Map<String,Object> searchMap, int page, int size);
}
