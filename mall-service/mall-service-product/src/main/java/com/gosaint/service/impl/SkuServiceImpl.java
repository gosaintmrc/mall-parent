package com.gosaint.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gosaint.dao.SkuMapper;
import com.gosaint.product.domain.Sku;
import com.gosaint.service.SkuService;

import org.springframework.stereotype.Service;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 20:34 2019/12/22
 * @Modified By:
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Resource
    private SkuMapper skuMapper;

    @Override public List<Sku> findAll() {
        return skuMapper.selectAll();
    }

    @Override
    public PageInfo<Sku> findPage(final int page, final int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(skuMapper.selectAll());
    }

}
