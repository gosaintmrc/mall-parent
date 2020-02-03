package com.mall.user.service;

import com.mall.domain.User;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 18:02 2020/2/2
 * @Modified By:
 */
public interface UserService {

    User findById(String username);
}
