package com.xiao.amovie.controller;

import com.xiao.amovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }
}
