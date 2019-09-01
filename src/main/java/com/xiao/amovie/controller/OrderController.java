package com.xiao.amovie.controller;

import com.xiao.amovie.entity.Scene;
import com.xiao.amovie.from.MovieScore;
import com.xiao.amovie.repository.SceneRepository;
import com.xiao.amovie.repository.ScoreRepository;
import com.xiao.amovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xiao
 * @date 2019-08-30 16:41
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SceneRepository sceneRepository;

    @GetMapping("/{id}")
    public String book1(@PathVariable("id") Integer id, Model model) {
        MovieScore movieScore = movieService.findByMovieId(id);
        List<Scene> sceneList = sceneRepository.findByMovieName(movieScore.getName());
        model.addAttribute("movie",movieScore);
        model.addAttribute("sceneList",sceneList);
        return "book1";
    }

    @GetMapping("/book2")    // 场次id
    public String book2(Model model, HttpServletRequest request) {
        String choosetime = request.getParameter("choosen-time");
        String movieName = request.getParameter("choosen-movie");
        String movieId = request.getParameter("choosen-movieId");
        String sceneId = request.getParameter("choosen-scene");
        String price = request.getParameter("choosen-price");

        return "book2";
    }
}
