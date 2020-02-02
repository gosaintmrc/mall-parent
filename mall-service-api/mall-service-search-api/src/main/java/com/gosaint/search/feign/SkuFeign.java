package com.gosaint.search.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 22:45 2020/1/24
 * @Modified By:
 */
@FeignClient(name = "search")
@RequestMapping("/search")
public interface SkuFeign {

    @GetMapping
    Map search(@RequestParam(required = false) Map<String,String> searchMap);
}
