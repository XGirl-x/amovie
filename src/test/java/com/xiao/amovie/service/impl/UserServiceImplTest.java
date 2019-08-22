package com.xiao.amovie.service.impl;

import com.xiao.amovie.entity.User;
import com.xiao.amovie.enums.Gender;
import com.xiao.amovie.enums.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void register() {
        boolean b = userService.register(new User("GGirl", "123456@qq.com", "12345", "18397872308", Gender.FEMALE.getCode(), Role.USER.getMessage()));
        Assert.assertEquals(true, b);
    }

    @Test
    public void login() {
        User user = userService.findByEmail("2233@qq.com");
        if (user != null) {
            boolean b = userService.login(user.getEmail(), "1234");
            Assert.assertEquals(true, b);
        }

    }
}