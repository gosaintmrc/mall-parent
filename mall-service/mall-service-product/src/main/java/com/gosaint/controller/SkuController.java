package com.gosaint.controller;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.gosaint.entity.PageResult;
import com.gosaint.entity.Result;
import com.gosaint.entity.StatusCode;
import com.gosaint.product.domain.Brand;
import com.gosaint.product.domain.Sku;
import com.gosaint.service.SkuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 20:29 2019/12/22
 * @Modified By:
 */
@RestController
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @GetMapping
    public Result<List<Sku>> findAll(){
        List<Sku> skuList=skuService.findAll();
        return new Result<>(true, StatusCode.OK,"查询SKU成功",skuList);
    }

    /***
     * 分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        PageInfo<Sku> pageInfo = skuService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"分页查询成功",pageInfo);
    }

}
