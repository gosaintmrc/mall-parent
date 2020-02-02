package com.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 16:20 2020/2/2
 * @Modified By:
 */
@SpringBootApplication
@EnableEurekaClient
public class GetWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetWayApplication.class,args);
    }
}
