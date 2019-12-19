package com.gosaint.config;

import javax.persistence.Id;

import com.gosaint.entity.IdWorker;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 19:19 2019/12/19
 * @Modified By:
 */
@Component
public class BeanConfig {

    /** 分布式id生成器*/
    @Bean
    public IdWorker getIdWorker(){
        return new IdWorker(0,0);
    }
}
