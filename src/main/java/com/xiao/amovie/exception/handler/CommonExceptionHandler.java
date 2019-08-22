package com.xiao.amovie.exception.handler;

import com.xiao.amovie.exception.CommonException;
import com.xiao.amovie.utils.ReturnVOUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author xiao
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity getException(CommonException common){
        return new ResponseEntity(ReturnVOUtil.error(common.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
