package com.xiao.amovie.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    UNPAID(0,"未支付"),
    PAID(1,"已支付")
    ;
    private Integer code;

    private String message;

    OrderStatus(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
