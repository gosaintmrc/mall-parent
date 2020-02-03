package com.mall.user;

import com.mall.domain.User;
import com.mall.user.dao.UserDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MicroSsoClient2ApplicationTests {

    @Autowired
    private UserDao userDao;
    @Test
    public void contextLoads() {
        User caozg = userDao.selectByPrimaryKey("caozg");
        System.out.println(caozg);
    }

}