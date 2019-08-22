package com.xiao.amovie.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiao.amovie.VO.MovieVO;
import com.xiao.amovie.entity.Category;
import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.entity.News;
import com.xiao.amovie.entity.Scene;
import com.xiao.amovie.repository.CategoryRepository;
import com.xiao.amovie.repository.MovieRepository;
import com.xiao.amovie.repository.SceneRepository;
import com.xiao.amovie.utils.Json;
import com.xiao.amovie.utils.ReturnVOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SceneRepository sceneRepository;

    @GetMapping
    public ResponseEntity getAll(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                 @RequestParam(value = "size",required = false,defaultValue = "20") Integer size) {
        Page<Movie> movieList = PageHelper.startPage(page, size).doSelectPage(() -> repository.getAll());
        List<MovieVO> movieVOList=new ArrayList<>();
        for (Movie movie: movieList){
            MovieVO movieVO = new MovieVO();
            BeanUtils.copyProperties(movie,movieVO);
            String categoryId = movieVO.getCategoryId();
            if (!StringUtils.isEmpty(categoryId)){
                List<Integer> list = Json.parseArray(categoryId, Integer.class);
                List<Category> temp = new ArrayList<>();
                for (Integer id : list){
                    Category category = categoryRepository.findById(id);
                    temp.add(category);
                }
                movieVO.setList(temp);
                movieVOList.add(movieVO);
            }
            movieVO.setList(null);
            movieVOList.add(movieVO);
        }
        return new ResponseEntity(movieVOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) {
        Movie movie = repository.findById(id);
        MovieVO movieVO = new MovieVO();
        BeanUtils.copyProperties(movie,movieVO);
        String categoryId = movieVO.getCategoryId();
        if (!StringUtils.isEmpty(categoryId)) {
            List<Integer> list = Json.parseArray(categoryId, Integer.class);
            List<Category> temp = new ArrayList<>();
            for (Integer ids : list) {
                Category category = categoryRepository.findById(ids);
                temp.add(category);
            }
            movieVO.setList(temp);
        }
        ResponseEntity responseEntity;
        if (movieVO!=null){
            responseEntity = new ResponseEntity(movieVO,HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity(ReturnVOUtil.notFound(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody Movie movie) {
        int i = repository.insert(movie);
        ResponseEntity responseEntity;
        if (i>0){
            responseEntity = new ResponseEntity(ReturnVOUtil.success(),HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity(ReturnVOUtil.createFail("创建失败"),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @RequestBody Movie movie) {
        Movie movie1 = repository.findById(id);
        if (movie1 == null){
            return new ResponseEntity(ReturnVOUtil.notFound(),HttpStatus.NOT_FOUND);
        }
        movie.setId(id);
        int i = repository.update(movie);
        if (i>0){
            return new ResponseEntity(ReturnVOUtil.success(),HttpStatus.OK);
        }
        return new ResponseEntity(ReturnVOUtil.createFail("更新失败"),HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        Movie movie = repository.findById(id);
        if (movie == null){
            return new ResponseEntity(ReturnVOUtil.notFound(),HttpStatus.NOT_FOUND);
        }
        int i = repository.delete(id);
        if (i>0){
            return new ResponseEntity(ReturnVOUtil.success(),HttpStatus.OK);
        }
        return new ResponseEntity(ReturnVOUtil.createFail("删除失败"),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}/scenes")
    public ResponseEntity getMovieScenes(@PathVariable("id") Integer id) {
        Movie movie = repository.findById(id);
        if (movie == null){
            return new ResponseEntity(ReturnVOUtil.notFound(),HttpStatus.NOT_FOUND);
        }
        List<Scene> sceneList = sceneRepository.findByMovieName(movie.getName());
        if (sceneList.size()==0){
            return new ResponseEntity(null,HttpStatus.OK);
        }
        return new ResponseEntity(sceneList,HttpStatus.OK);
    }
}
