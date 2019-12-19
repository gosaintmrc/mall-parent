package com.gosaint.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 23:28 2019/12/18
 * @Modified By:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
