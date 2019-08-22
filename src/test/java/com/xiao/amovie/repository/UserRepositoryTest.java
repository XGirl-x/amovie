package com.xiao.amovie.repository;

import com.xiao.amovie.entity.User;
import com.xiao.amovie.enums.Gender;
import com.xiao.amovie.enums.Role;
import com.xiao.amovie.utils.MD5Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void findByEmail() {
        User user = repository.findByEmail("283710867@qq.com");
        Assert.assertNotNull(user);
    }

    @Test
    public void delete() {
        int i = repository.delete(8);
        Assert.assertEquals(1, i);
    }

    @Test
    public void update() {
        User user = repository.findById(5);
        String salt = MD5Utils.getSalt();
        String encryptPassword = MD5Utils.md5("111", salt);
        user.setNickname("xiao");
        user.setEmail("111@qq.com");
        user.setPassword(encryptPassword);
        user.setSalt(salt);
        user.setPhone("111");

        int i = repository.update(user);

        Assert.assertEquals(1, i);

    }
}