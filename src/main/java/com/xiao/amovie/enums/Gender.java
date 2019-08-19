package com.xiao.amovie.enums;

import lombok.Getter;

@Getter
public enum Gender {
    FEMALE(0,"女"),
    MALE(1,"男"),
    OTHER(2,"其他")
    ;
    private Integer code;

    private String message;

    Gender(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
