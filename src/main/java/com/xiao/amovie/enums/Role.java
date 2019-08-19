package com.xiao.amovie.enums;

import lombok.Getter;

@Getter
public enum Role {
    USER("普通用户"),
    ADMIN("管理员")
    ;
    private String message;

    Role(String message){
        this.message = message;
    }
}
