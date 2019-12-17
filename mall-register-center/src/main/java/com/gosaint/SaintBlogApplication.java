package com.gosaint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author gosaint
 */
@SpringBootApplication
@EnableEurekaServer
public class SaintBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaintBlogApplication.class, args);
    }

}