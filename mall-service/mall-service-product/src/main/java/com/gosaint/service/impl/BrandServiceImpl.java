package com.gosaint.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.gosaint.dao.BrandMapper;
import com.gosaint.product.domain.Brand;
import com.gosaint.service.BrandService;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tk.mybatis.mapper.entity.Example;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 0:00 2019/12/17
 * @Modified By:
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandMapper brandMapper;
    @Override public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public List<Brand> findList(final Brand brand) {
        return brandMapper.selectByExample(createExample(brand));
    }

    private Example createExample(Brand brand){
        Example example=new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(brand!=null){
            if(!StringUtils.isEmpty(brand.getName())){
                /** String property, String value */
                criteria.andLike("name","%"+brand.getName()+"%");
            }
            if(!StringUtils.isEmpty(brand.getLetter())){
                criteria.andEqualTo("letter",brand.getLetter());
            }
        }
        return example;
    }

    @Override
    public Brand findById(final Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * Selective可以避免空指针异常
     * @param brand
     */
    @Override
    public void add(final Brand brand) {
        brandMapper.insertSelective(brand);
    }

    @Override
    public void update(final Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(final Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }
}
