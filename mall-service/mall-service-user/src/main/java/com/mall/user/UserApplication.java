package com.mall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 17:48 2020/2/2
 * @Modified By:
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.mall.user.dao")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
