package com.mall.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.mall.domain.User;
import com.mall.user.dao.UserDao;
import com.mall.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 18:02 2020/2/2
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public User findById(final String username) {
        User user = userDao.selectByPrimaryKey(username);
        System.out.println(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userDao.selectAll();
    }
}
