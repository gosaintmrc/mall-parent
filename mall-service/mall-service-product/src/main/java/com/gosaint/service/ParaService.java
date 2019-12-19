package com.gosaint.service;

import java.util.List;

import com.gosaint.product.domain.Para;

/**
 * @Author: gosaint
 * @Description: 商品参数
 * @Date Created in 21:08 2019/12/19
 * @Modified By:
 */
public interface ParaService {

    /**
     * 根据分类id查询商品参数集合
     * @param categoryId
     * @return
     */
    List<Para> findByCategory(Integer categoryId);
}
