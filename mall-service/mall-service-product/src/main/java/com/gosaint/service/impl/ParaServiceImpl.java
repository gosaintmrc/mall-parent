package com.gosaint.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.gosaint.dao.CategoryMapper;
import com.gosaint.dao.ParaMapper;
import com.gosaint.product.domain.Category;
import com.gosaint.product.domain.Para;
import com.gosaint.service.ParaService;

import org.springframework.stereotype.Service;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 21:10 2019/12/19
 * @Modified By:
 */
@Service
public class ParaServiceImpl implements ParaService {

    @Resource
    private ParaMapper paraMapper;

    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public List<Para> findByCategory(final Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Para para=new Para();
        para.setTemplateId(category.getTemplateId());
        return paraMapper.select(para);
    }
}
