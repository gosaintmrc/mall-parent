package com.gosaint.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.gosaint.dao.CategoryMapper;
import com.gosaint.product.domain.Category;
import com.gosaint.service.CategoryService;

import org.springframework.stereotype.Service;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 19:33 2019/12/19
 * @Modified By:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> findParentId(final Integer pid) {
        /**
         * 封装JavaBean,如果JavaBean的属性不为空，那么就会将属性值作为条件去查询
         */
        Category category=new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
    }
}
