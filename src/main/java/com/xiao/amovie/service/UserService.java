package com.xiao.amovie.service;

import com.xiao.amovie.entity.User;

/**
 * @author xiao
 */
public interface UserService {

    boolean isExistUser(String email);

    User findByEmail(String email);

    boolean register(User user);

    boolean login(String email, String password);

}
