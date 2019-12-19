package com.gosaint.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.gosaint.dao.CategoryMapper;
import com.gosaint.dao.SpecMapper;
import com.gosaint.product.domain.Category;
import com.gosaint.product.domain.Spec;
import com.gosaint.service.SpecService;

import org.springframework.stereotype.Service;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 20:54 2019/12/19
 * @Modified By:
 */
@Service
public class SpecServiceImpl implements SpecService {

    @Resource
    private SpecMapper specMapper;

    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public List<Spec> findByCategory(final Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Spec spec=new Spec();
        spec.setTemplateId(category.getTemplateId());
        return specMapper.select(spec);
    }
}
