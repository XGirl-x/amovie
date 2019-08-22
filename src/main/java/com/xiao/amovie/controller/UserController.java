package com.xiao.amovie.controller;

import com.xiao.amovie.from.ResultForm;
import com.xiao.amovie.entity.User;
import com.xiao.amovie.enums.Gender;
import com.xiao.amovie.enums.Role;
import com.xiao.amovie.service.UserService;
import com.xiao.amovie.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xiao
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public ResultForm register() {
        User user = new User("GG", "1111@qq.com", "123", "18397872308", Gender.MALE.getCode(), Role.USER.getMessage());
        boolean b = userService.register(user);
        if (b){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("注册失败");
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultForm login(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam(value = "loginStatus",defaultValue = "off") String loginStatus, HttpSession session, HttpServletResponse response){
        boolean b = userService.login(email, password);
        User user = userService.findByEmail(email);
        if (b){
            session.setAttribute("user",user);
            if ("on".equals(loginStatus)){
                response.addCookie(new Cookie("userId",user.getId().toString()));
                response.addCookie(new Cookie("userName",user.getNickname()));
            }
            return ResultVOUtil.success();
        }

        return ResultVOUtil.error("用户名或密码错误");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
