package com.xiao.amovie.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.entity.Scene;
import com.xiao.amovie.exception.CommonException;
import com.xiao.amovie.exception.NotFoundException;
import com.xiao.amovie.from.MovieForm;
import com.xiao.amovie.repository.CategoryRepository;
import com.xiao.amovie.repository.MovieRepository;
import com.xiao.amovie.repository.SceneRepository;
import com.xiao.amovie.service.MovieService;
import com.xiao.amovie.utils.ReturnVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private CategoryRepository categoryRepository;

    @Autowired
    private SceneRepository sceneRepository;

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity getAll(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                 @RequestParam(value = "size",required = false,defaultValue = "20") Integer size) {
        Page<Movie> movieList = PageHelper.startPage(page, size).doSelectPage(() -> repository.getAll());
        return new ResponseEntity(movieList.toPageInfo(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) {
        Movie movie = repository.findById(id);
        if (movie!=null){
            return new ResponseEntity(movie,HttpStatus.OK);
        }
            throw new NotFoundException("资源未找到");
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody MovieForm movieForm) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieForm,movie);
        Integer[] categoryIds = movieForm.getCategoryIds();
        movieService.insert(movie, categoryIds);
        return new ResponseEntity(ReturnVOUtil.success(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @RequestBody MovieForm movieForm) {
        Movie movie = repository.findById(id);
        if (movie == null){
            throw new NotFoundException("资源未找到");
        }
        movieService.delete(id);
        Movie movie1 = new Movie();
        BeanUtils.copyProperties(movieForm,movie1);
        Integer[] categoryIds = movieForm.getCategoryIds();
        movieService.insert(movie1,categoryIds);

        return new ResponseEntity(ReturnVOUtil.success(),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        Movie movie = repository.findById(id);
        if (movie == null){
            throw new NotFoundException("资源未找到");
        }
        int i = movieService.delete(id);
        if (i>0){
            return new ResponseEntity(ReturnVOUtil.success(),HttpStatus.OK);
        }
        throw new CommonException("删除失败");
    }

    @GetMapping("/{id}/scenes")
    public ResponseEntity getMovieScenes(@PathVariable("id") Integer id) {
        Movie movie = repository.findById(id);
        if (movie == null){
            throw new NotFoundException("资源未找到");
        }
        List<Scene> sceneList = sceneRepository.findByMovieName(movie.getName());
        if (sceneList.size()==0){
            return new ResponseEntity(null,HttpStatus.OK);
        }
        return new ResponseEntity(sceneList,HttpStatus.OK);
    }
}
