package com.xiao.amovie.configure;

import com.xiao.amovie.utils.MyProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author xiao
 * @date 2019-08-27 11:54
 */
@Configuration
public class MyConfiguration implements WebMvcConfigurer {

    private final MyProperties properties;

    public MyConfiguration(MyProperties properties) {
        this.properties = properties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + properties.getPath() + File.separator);
    }
}


