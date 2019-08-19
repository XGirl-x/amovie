package com.xiao.amovie.repository;

import com.xiao.amovie.entity.User;
import com.xiao.amovie.enums.Gender;
import com.xiao.amovie.enums.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void insert() {
        int res = repository.insert(new User("XGirl", "283710867@qq.com", "123", "125", "18397872308", Gender.FEMALE.getCode(), Role.USER.getMessage()));
        Assert.assertEquals(1, res);
    }

    @Test
    public void findByEmail(){
        User user = repository.findByEmail("283710867@qq.com");
        Assert.assertNotNull(user);
    }
}