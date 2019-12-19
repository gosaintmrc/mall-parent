package com.gosaint.controller;

import java.util.List;

import com.gosaint.entity.Result;
import com.gosaint.entity.StatusCode;
import com.gosaint.product.domain.Para;
import com.gosaint.service.ParaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gosaint
 * @Description: 商品参数
 * @Date Created in 21:06 2019/12/19
 * @Modified By:
 */
@RestController
@RequestMapping("/para")
@CrossOrigin
public class ParaController {


    @Autowired
    private ParaService paraService;

    /**
     * 根据商品分类id查询商品参数
     * @param categoryId
     * @return
     */
    @GetMapping("/category/{id}")
    public Result<List<Para>> findByCategoryId(@PathVariable(value = "id")Integer categoryId){
        List<Para> paraList = paraService.findByCategory(categoryId);
        return new Result<>(true, StatusCode.OK,"根据商品id查询参数集合成功",paraList);
    }
}
