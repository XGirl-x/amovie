package com.xiao.amovie.enums;

import lombok.Getter;

/**
 * 上映状态
 * @author xiao
 */
@Getter
public enum Status {
    NOTSHOWN(0,"未上映"),
    RELEASED(1,"已上映")
    ;
    private Integer code;

    private String message;

    Status(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
