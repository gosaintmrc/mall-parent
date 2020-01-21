package com.gosaint.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.gosaint.product.domain.Sku;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 20:34 2019/12/22
 * @Modified By:
 */
public interface SkuService {

    /**
     * 查询所有的SKU
     * @return
     */
    List<Sku> findAll();

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Sku> findPage(final int page, final int size);
}
