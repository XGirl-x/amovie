package com.xiao.amovie.controller;

import com.xiao.amovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiao
 */
@Controller
public class RouterController {

    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }

    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }

    @RequestMapping("/book1.html")
    public String book1() {
        return "book1";
    }

    @RequestMapping("/book2.html")
    public String book2() {
        return "book2";
    }

    @RequestMapping("/book3-buy.html")
    public String book3() {
        return "book3-buy";
    }

    @RequestMapping("/book-final.html")
    public String bookFinal() {
        return "book-final";
    }

    @RequestMapping("/movie-list.html")
    public String movieList() {
        return "movie-list";
    }

    @RequestMapping("/rate.html")
    public String rate() {
        return "rate";
    }

    @RequestMapping("/news-list.html")
    public String newsList() {
        return "news-list";
    }

    @RequestMapping("/watchlist.html")
    public String watchlist() {
        return "watchlist";
    }

    @RequestMapping("/ticket.html")
    public String ticket() {
        return "ticket";
    }




}
