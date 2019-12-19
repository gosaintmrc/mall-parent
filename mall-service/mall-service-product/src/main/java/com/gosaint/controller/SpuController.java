package com.gosaint.controller;

import java.util.Map;

import com.github.pagehelper.Page;
import com.gosaint.entity.PageResult;
import com.gosaint.entity.Result;
import com.gosaint.entity.StatusCode;
import com.gosaint.product.domain.Products;
import com.gosaint.product.domain.Spu;
import com.gosaint.service.SpuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 22:17 2019/12/19
 * @Modified By:
 */
@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {
    @Autowired
    private SpuService spuService;

    @PostMapping("/save")
    public Result saveGoods(@RequestBody Products products) {
        spuService.saveGoods(products);
        return new Result(true, StatusCode.OK, "保存商品成功");
    }

    @GetMapping(value = "/goods/{id}")
    public Result<Products> findGoosById(@PathVariable(value = "id") Long spuId) {
        Products goods = spuService.findGoodsById(spuId);
        return new Result<>(true, StatusCode.OK, "查询成功", goods);
    }

    /***
     * 审核 *
     * @param spuId
     * @return
     * */
    @PutMapping("/audit/{id}")
    public Result audit(@PathVariable(value = "id")Long spuId) {
        spuService.audit(spuId);
        return new Result(true,StatusCode.OK,"审核通过");
    }

    /***
     * 商品下架
     * @param spuId
     * @return
     * */
    @PutMapping("/pull/{id}")
    public Result pull(@PathVariable(value = "id")Long spuId) {
        spuService.pull(spuId);
        return new Result(true,StatusCode.OK,"商品下架成功");
    }

    /***
     * 商品上架
     * @param spuId
     * @return
     * */
    @PutMapping("/put/{id}")
    public Result put(@PathVariable(value = "id")Long spuId) {
        spuService.put(spuId);
        return new Result(true,StatusCode.OK,"商品上架成功");
    }

    /***
     * 商品批量上架
     * @param ids
     * @return
     * */
    @PutMapping("/put/many")
    public Result batchPut(@RequestBody Long[] ids) {
        spuService.batchPut(ids);
        return new Result(true,StatusCode.OK,"商品批量上架成功");
    }

    /***
     * 分页搜索实现
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result findPage(@RequestParam Map searchMap, @PathVariable  int page, @PathVariable  int size){
        Page<Spu> pageList = spuService.findPage(searchMap, page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
}


