package com.xiao.amovie.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.entity.Review;
import com.xiao.amovie.entity.Scene;
import com.xiao.amovie.exception.CommonException;
import com.xiao.amovie.exception.NotFoundException;
import com.xiao.amovie.from.MovieForm;
import com.xiao.amovie.from.ReviewForm;
import com.xiao.amovie.repository.*;
import com.xiao.amovie.service.MovieService;
import com.xiao.amovie.service.ReviewService;
import com.xiao.amovie.utils.ReturnVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author xiao
 */
@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private SceneRepository sceneRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private WatchListRepository watchListRepository;


    @GetMapping
    public String getAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                         @RequestParam(value = "size", required = false, defaultValue = "3") Integer size,
                         Model model) {
        PageInfo<Movie> movieList = PageHelper.startPage(page, size).doSelectPageInfo(() -> repository.getAll());
        model.addAttribute("movieList",movieList);
        return "movie-list";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Integer id, Model model) {
        Movie movie = repository.findById(id);
        if (movie != null) {
            List<Scene> sceneList = sceneRepository.findByMovieName(movie.getName());
            List<ReviewForm> reviewFormList = reviewService.findByMovieId(id);
            int count = reviewRepository.getCount(id);
            model.addAttribute("movie",movie);
            model.addAttribute("sceneList",sceneList);
            model.addAttribute("reviewList",reviewFormList);
            model.addAttribute("count",count);
            return "movie";
        }
        throw new NotFoundException("资源未找到");
    }

    @GetMapping("/watch/{id}")
    public String getDetailById(@PathVariable("id") Integer id, Model model) {
        Movie movie = repository.findById(id);
        if (movie != null) {
            List<Scene> sceneList = sceneRepository.findByMovieName(movie.getName());
            List<ReviewForm> reviewFormList = reviewService.findByMovieId(id);
            int count = reviewRepository.getCount(id);
            model.addAttribute("movie",movie);
            model.addAttribute("sceneList",sceneList);
            model.addAttribute("reviewList",reviewFormList);
            model.addAttribute("count",count);
            return "moviedetail";
        }
        throw new NotFoundException("资源未找到");
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody MovieForm movieForm) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieForm, movie);
        Integer[] categoryIds = movieForm.getCategoryIds();
        movieService.insert(movie, categoryIds);
        return new ResponseEntity(ReturnVOUtil.success(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @RequestBody MovieForm movieForm) {
        Movie movie = repository.findById(id);
        if (movie == null) {
            throw new NotFoundException("资源未找到");
        }
        movieService.delete(id);
        Movie movie1 = new Movie();
        BeanUtils.copyProperties(movieForm, movie1);
        Integer[] categoryIds = movieForm.getCategoryIds();
        movieService.insert(movie1, categoryIds);

        return new ResponseEntity(ReturnVOUtil.success(), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        Movie movie = repository.findById(id);
        if (movie == null) {
            throw new NotFoundException("资源未找到");
        }
        int i = movieService.delete(id);
        if (i > 0) {
            return new ResponseEntity(ReturnVOUtil.success(), HttpStatus.OK);
        }
        throw new CommonException("删除失败");
    }

    /*@GetMapping("/{id}/scenes")
    public ResponseEntity getMovieScenes(@PathVariable("id") Integer id) {
        Movie movie = repository.findById(id);
        if (movie == null) {
            throw new NotFoundException("资源未找到");
        }
        List<Scene> sceneList = sceneRepository.findByMovieName(movie.getName());
        if (sceneList.size() == 0) {
            return new ResponseEntity(null, HttpStatus.OK);
        }
        return new ResponseEntity(sceneList, HttpStatus.OK);
    }*/
}
