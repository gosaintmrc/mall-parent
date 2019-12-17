package com.gosaint.controller;

import java.util.List;

import com.gosaint.entity.Result;
import com.gosaint.entity.StatusCode;
import com.gosaint.product.domain.Brand;
import com.gosaint.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 0:01 2019/12/17
 * @Modified By:
 * @CrossOrigin 跨域 A域名访问B域名的数据
 */
@RestController
@RequestMapping("/brand")
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 查询所有平拍
     * @return
     */
    @GetMapping
    public Result<List<Brand>> findAll(){
        List<Brand> brands = brandService.findAll();
        return new Result<>(true, StatusCode.OK,"查询品牌集合成功",brands);
    }

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable(value = "id")Integer id){
        Brand brand = brandService.findById(id);
        return new Result<>(true, StatusCode.OK,"查询单个品牌成功",brand);
    }

    /**
     * 添加品牌
     * @param brand
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result<>(true, StatusCode.OK,"增加品牌成功");
    }

    /**
     * 根据id修改品牌
     * @param id
     * @param brand
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable(value = "id") Integer id,@RequestBody Brand brand){
        brand.setId(id);
        brandService.update(brand);
        return new Result<>(true, StatusCode.OK,"修改品牌成功");
    }


    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        brandService.delete(id);
        return new Result(true,StatusCode.OK,"删除品牌成功");
    }


    /***
     * 多条件搜索品牌数据
     * @param brand
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Brand>> findList(@RequestBody(required = false) Brand brand){
        List<Brand> list = brandService.findList(brand);
        return new Result<List<Brand>>(true,StatusCode.OK,"查询成功",list);
    }

}
