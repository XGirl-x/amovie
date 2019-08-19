package com.xiao.amovie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiao.amovie.repository")
public class AmovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmovieApplication.class, args);
    }

}
