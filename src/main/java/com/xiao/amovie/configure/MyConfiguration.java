package com.xiao.amovie.configure;

import com.xiao.amovie.interceptor.LoginStatusInterceptor;
import com.xiao.amovie.utils.MyProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    private final LoginStatusInterceptor loginStatusInterceptor;

    public MyConfiguration(MyProperties properties, LoginStatusInterceptor loginStatusInterceptor) {
        this.properties = properties;
        this.loginStatusInterceptor = loginStatusInterceptor;
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + properties.getPath() + File.separator);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginStatusInterceptor)
                .addPathPatterns("/order/**","/watchlists/**");
    }
}


