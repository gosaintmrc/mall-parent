package com.gosaint.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 23:29 2019/12/18
 * @Modified By:
 */
@Controller
public class IndexController {


    @RequestMapping("/")
    public String index(){
        return "index2";
    }
}
