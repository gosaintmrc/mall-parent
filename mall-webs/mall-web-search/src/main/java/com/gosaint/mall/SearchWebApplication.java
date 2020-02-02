package com.gosaint.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 23:28 2020/1/24
 * @Modified By:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.gosaint.search.feign")
public class SearchWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchWebApplication.class,args);
    }
}
