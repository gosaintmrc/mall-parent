package com.mall.user.controller;

import com.gosaint.entity.BCrypt;
import com.gosaint.entity.BCryptUtil;
import com.gosaint.entity.Result;
import com.gosaint.entity.StatusCode;
import com.mall.domain.User;
import com.mall.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 17:59 2020/2/2
 * @Modified By:
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Result login(
            @RequestParam String username,
            @RequestParam String password) {
        User user = userService.findById(username);
        if (BCryptUtil.isMatch(password, user.getPassword())) {
            return new Result(true, StatusCode.OK, "登录成功！", user);
        }
        return new Result(false, StatusCode.LOGINERROR, "用户名或密码错误！");
    }

    @PostMapping(value = "/register")
    public Result register(String username, String password) {
        User user = userService.findById(username);
        if (BCrypt.checkpw(password, user.getPassword())) {
            return new Result(true, StatusCode.OK, "登录成功！", user);
        }
        return new Result(false, StatusCode.LOGINERROR, "用户名或密码错误！");
    }
}
