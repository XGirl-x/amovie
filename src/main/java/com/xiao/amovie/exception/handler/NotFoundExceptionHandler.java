package com.xiao.amovie.exception.handler;

import com.xiao.amovie.exception.NotFoundException;
import com.xiao.amovie.utils.ReturnVOUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author xiao
 */
@ControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity getException(NotFoundException e) {
        return new ResponseEntity(ReturnVOUtil.error(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
