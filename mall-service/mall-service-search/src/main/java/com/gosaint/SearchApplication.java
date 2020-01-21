package com.gosaint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 18:53 2019/12/22
 * @Modified By:
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.gosaint.product.feign")
@EnableElasticsearchRepositories(basePackages = "com.gosaint.dao")
public class SearchApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors","false");
        SpringApplication.run(SearchApplication.class,args);
    }
}
