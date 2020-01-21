package com.gosaint.product.feign;

import java.util.List;

import com.gosaint.entity.Result;
import com.gosaint.product.domain.Sku;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 20:26 2019/12/22
 * @Modified By:
 */
@FeignClient(value = "products")
@RequestMapping("/sku")
public interface SkuFeign {

    /**
     * 查询全部SKU
     * @return
     */
    @GetMapping
    Result<List<Sku>> findAll();
}
