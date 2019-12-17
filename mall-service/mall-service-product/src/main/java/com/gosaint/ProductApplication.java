package com.gosaint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: gosaint
 * @Description:
 * @Date Created in 23:43 2019/12/16
 * @Modified By:
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.gosaint.dao"})
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class,args);
    }
}
