package com.gosaint.controller;

import java.util.List;

import com.gosaint.entity.Result;
import com.gosaint.entity.StatusCode;
import com.gosaint.product.domain.Category;
import com.gosaint.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 19:43 2019/12/19
 * @Modified By:
 */
@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list/{pid}")
    public Result<List<Category>> findByParentId(@PathVariable(value = "pid")Integer pid){
        List<Category> categoryList = categoryService.findParentId(pid);
        return new Result<>(true, StatusCode.OK,"查询商品分类子节点成功",categoryList);
    }
}
