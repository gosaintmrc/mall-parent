package com.gosaint.service;

import java.util.List;

import com.gosaint.product.domain.Category;

/**
 * @Author: gosaint
 * @Description: 商品分类服务
 * @Date Created in 19:24 2019/12/19
 * @Modified By:
 */
public interface CategoryService {
    /**
     * 根据父节点id查询子节点
     * sample:SELECT * FROM tb_category WHERE parent_id=1;
     * @param pid 父节点id
     * @return
     */
    List<Category> findParentId(Integer pid);
}
