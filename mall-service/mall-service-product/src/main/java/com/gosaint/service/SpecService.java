package com.gosaint.service;

import java.util.List;

import com.gosaint.product.domain.Spec;

/**
 * @Author: gosaint
 * @Description: 商品规格
 * @Date Created in 20:54 2019/12/19
 * @Modified By:
 */
public interface SpecService {
    /**
     * 根据商品分类id查询规格List集合
     * @param categoryId
     * @return
     */
    List<Spec> findByCategory(Integer categoryId);
}
