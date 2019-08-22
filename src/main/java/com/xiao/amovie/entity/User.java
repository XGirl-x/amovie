package com.xiao.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author xiao
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String nickname;

    private String email;

    private String password;

    private String salt;

    private String phone;

    /**
     * 性别
     * {@link com.xiao.amovie.enums.Gender}
     */
    private Integer gender;

    /**
     * 角色
     * {@link com.xiao.amovie.enums.Role}
     */
    private String role;

    public User(String nickname, String email, String password, String salt, String phone, Integer gender, String role) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }

    public User(String nickname, String email, String password, String phone, Integer gender, String role) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }
}
