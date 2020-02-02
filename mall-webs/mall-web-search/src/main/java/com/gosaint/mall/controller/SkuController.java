package com.gosaint.mall.controller;

import java.util.Map;

import javax.annotation.Resource;

import com.gosaint.search.feign.SkuFeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 14:29 2020/2/2
 * @Modified By:
 */
@Controller
@RequestMapping(value = "/search")
public class SkuController {
    @Resource
    private SkuFeign skuFeign;

    @GetMapping("/list")
    public String search(@RequestParam(required = false)Map searchMap, Model model){
        Map resultMap = skuFeign.search(searchMap);
        model.addAttribute("result",resultMap);
        model.addAttribute("searchMap",searchMap);
        return "search";
    }
}
