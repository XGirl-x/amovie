package com.xiao.amovie.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.amovie.entity.News;
import com.xiao.amovie.entity.User;
import com.xiao.amovie.enums.Gender;
import com.xiao.amovie.enums.Role;
import com.xiao.amovie.enums.Status;
import com.xiao.amovie.from.MovieScore;
import com.xiao.amovie.from.ResultForm;
import com.xiao.amovie.from.UserForm;
import com.xiao.amovie.repository.NewsRepository;
import com.xiao.amovie.repository.UserRepository;
import com.xiao.amovie.service.MovieService;
import com.xiao.amovie.service.UserService;
import com.xiao.amovie.utils.EmailRegex;
import com.xiao.amovie.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiao
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsRepository repository;

    @Autowired
    private MovieService movieService;

    @GetMapping(value = {"/","/index"})
    public String newsIndex(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                            @RequestParam(value = "size", required = false, defaultValue = "6") Integer size,
                            Model model) {
        PageInfo<News> newsList = PageHelper.startPage(page, 3).doSelectPageInfo(() -> repository.getAll());
        PageInfo<MovieScore> selectPageInfo = PageHelper.startPage(page, size).doSelectPageInfo(() -> movieService.findMovieScoreSort());
        PageInfo<MovieScore> movieScoreList = PageHelper.startPage(page,8).doSelectPageInfo(() -> movieService.findByStatus(Status.RELEASED.getCode()));
        PageInfo<MovieScore> movieScorePoster = PageHelper.startPage(page,3).doSelectPageInfo(() -> movieService.findByStatus(Status.RELEASED.getCode()));
        model.addAttribute("movieScore",selectPageInfo);
        model.addAttribute("movieScoreList",movieScoreList);
        model.addAttribute("newsList",newsList);
        model.addAttribute("movieScorePoster",movieScorePoster);
        return "index";
    }

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

    @GetMapping("/login")
    public String login() {
        return "login";
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
