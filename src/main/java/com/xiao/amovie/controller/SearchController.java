package com.xiao.amovie.controller;

import com.xiao.amovie.from.MovieScore;
import com.xiao.amovie.repository.MovieRepository;
import com.xiao.amovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiao
 * @date 2019-08-29 20:57
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @GetMapping("/1/{searchContent}")
    public String searchByTitle(@PathVariable("searchContent") String searchContent,
                                Model model) {
        List<MovieScore> movieScoreList = movieService.findByName(searchContent);
        model.addAttribute("movieLists",movieScoreList);
        return "movie-lists";
    }

    @GetMapping("/2/{searchContent}")
    public String searchByActor(@PathVariable("searchContent") String searchContent,
                                Model model) {
        List<MovieScore> movieScoreList = movieService.findByActors(searchContent);
        model.addAttribute("movieLists",movieScoreList);
        return "movie-lists";
    }

    @GetMapping("/3/{searchContent}")
    public String searchByDirector(@PathVariable("searchContent") String searchContent,
                                Model model) {
        List<MovieScore> movieScoreList = movieService.findByDirectors(searchContent);
        model.addAttribute("movieLists",movieScoreList);
        return "movie-lists";
    }

    @GetMapping("/4/{searchContent}")
    public String searchByCountry(@PathVariable("searchContent") String searchContent,
                                   Model model) {
        List<MovieScore> movieScoreList = movieService.findByCountry(searchContent);
        model.addAttribute("movieLists",movieScoreList);
        return "movie-lists";
    }

}
