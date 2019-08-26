package com.xiao.amovie.utils;

/**
 * 邮箱格式验证
 * @author xiao
 * @date 2019-08-23 11:12
 */
public class EmailRegex {

    public boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        return email.matches(emailRegex);
    }
}
