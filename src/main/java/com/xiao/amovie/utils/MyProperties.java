package com.xiao.amovie.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xiao
 * @date 2019-08-27 10:08
 */
@Data
@Component
public class MyProperties {

    @Value("${uploadLocation}")
    private String path;
}
