package com.xiao.amovie.controller;

import com.xiao.amovie.entity.User;
import com.xiao.amovie.enums.Gender;
import com.xiao.amovie.enums.Role;
import com.xiao.amovie.from.ResultForm;
import com.xiao.amovie.from.UserForm;
import com.xiao.amovie.repository.UserRepository;
import com.xiao.amovie.service.UserService;
import com.xiao.amovie.utils.EmailRegex;
import com.xiao.amovie.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiao
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;

    /*@PostMapping("/register")
    public ResultForm register(HttpServletRequest request) {
        String nickname = request.getParameter("name");
        String password = request.getParameter("password");
        String onfirmpPassword = request.getParameter("onfirmpPassword");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Integer gender = Integer.valueOf(request.getParameter("gender"));
        if (!onfirmpPassword.equals(password) || onfirmpPassword==""){
            return ResultVOUtil.error("密码输入有误");
        }
        EmailRegex emailRegex = new EmailRegex();
        if (!emailRegex.isEmail(email)){
            return ResultVOUtil.error("邮箱格式错误！");
        }
        User user = repository.findByEmail(email);
        if (user != null){
            return ResultVOUtil.error("该邮箱已被注册");
        }
        User user1 = new User(nickname, email, password, phone, gender, Role.USER.getMessage());
        boolean b = userService.register(user1);
        if (b) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("注册失败");
    }*/

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity register (@RequestBody @Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldError().getDefaultMessage();
            return new ResponseEntity("用户信息不完整", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        boolean b = userService.register(user);
        Map<String, Object> map = new HashMap<>();
        if (b) {
            map.put("message","注册成功");
            return ResponseEntity.ok(map);
        }else {
            map.put("message","注册失败");
            return new ResponseEntity(map,HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    @ResponseBody
    public ResultForm login(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam(value = "loginStatus", defaultValue = "off") String loginStatus, HttpSession session, HttpServletResponse response) {
        boolean b = userService.login(email, password);
        User user = userService.findByEmail(email);
        if (b) {
            session.setAttribute("user", user);
            if ("on".equals(loginStatus)) {
                response.addCookie(new Cookie("userId", user.getId().toString()));
                response.addCookie(new Cookie("userName", user.getNickname()));
            }
            return ResultVOUtil.success();
        }

        return ResultVOUtil.error("用户名或密码错误");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
