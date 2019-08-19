package com.xiao.amovie.service.impl;

import com.xiao.amovie.entity.User;
import com.xiao.amovie.exception.CommonException;
import com.xiao.amovie.repository.UserRepository;
import com.xiao.amovie.service.UserService;
import com.xiao.amovie.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;


    @Override
    public boolean isExistUser(String email) {
        User user = repository.findByEmail(email);
        return user!=null;
    }

    @Override
    public User findByEmail(String email) {
        User user = repository.findByEmail(email);
        return user;
    }

    @Override
    public boolean register(User user) {
        boolean existUser = isExistUser(user.getEmail());
        if (existUser){
            throw new CommonException("该邮箱已被注册");
        }
        else if (!existUser) {
            String salt = MD5Utils.getSalt();
            String encryptPassword = MD5Utils.md5(user.getPassword(),salt);
            user.setPassword(encryptPassword);
            user.setSalt(salt);
            int i = repository.insert(user);
            if (i>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean login(String email,String password) {
        User user = repository.findByEmail(email);
        if (user!=null){
            String salt = user.getSalt();
            String encryptPassword = MD5Utils.md5(password,salt);
            return encryptPassword.equals(user.getPassword());
        }
        return false;
    }
}
