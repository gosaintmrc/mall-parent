package com.gosaint.controller;

import java.util.List;

import com.gosaint.entity.Result;
import com.gosaint.entity.StatusCode;
import com.gosaint.product.domain.Spec;
import com.gosaint.service.SpecService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gosaint
 * @Description: 商品规格
 * @Date Created in 20:55 2019/12/19
 * @Modified By:
 */
@RestController
@RequestMapping("/spec")
@CrossOrigin
public class SpecController {

    @Autowired
    private SpecService specService;

    @GetMapping("/category/{id}")
    public Result<List<Spec>> findByCategoryId(@PathVariable("id")Integer categoryId){
        List<Spec> specList = specService.findByCategory(categoryId);
        return new Result<>(true, StatusCode.OK,"根据分类id查询规格集合成功",specList);
    }
}
