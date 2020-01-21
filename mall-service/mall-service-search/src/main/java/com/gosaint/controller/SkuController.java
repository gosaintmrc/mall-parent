package com.gosaint.controller;

import java.util.Map;

import com.gosaint.entity.Result;
import com.gosaint.entity.StatusCode;
import com.gosaint.service.SkuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 10:50 2020/1/15
 * @Modified By:
 */
@RestController
@CrossOrigin
@RequestMapping("/search")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @GetMapping("/import")
    public Result importData(){
        skuService.imports();
        return new Result(true, StatusCode.OK,"导入索引库成功");
    }

    @GetMapping
    public Map search(@RequestParam(required = false) Map<String,String> searchMap){
      return skuService.search(searchMap);
    }
}
