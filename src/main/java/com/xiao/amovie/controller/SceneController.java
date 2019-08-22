package com.xiao.amovie.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiao.amovie.VO.SceneVO;
import com.xiao.amovie.entity.Category;
import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.entity.Scene;
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
@RequestMapping("/scenes")
public class SceneController {

    @Autowired
    private SceneRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity getAll(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                 @RequestParam(value = "size",required = false,defaultValue = "20") Integer size) {
        Page<Scene> sceneList = PageHelper.startPage(page, size).doSelectPage(() -> repository.getAll());
        List<SceneVO> sceneVOList = new ArrayList<>();
        for (Scene scene : sceneList){
            SceneVO sceneVO = new SceneVO();
            BeanUtils.copyProperties(scene,sceneVO);
            String bookedSeat = sceneVO.getBookedSeat();
            if (!StringUtils.isEmpty(bookedSeat)){
                List<String> list = Json.parseArray(bookedSeat,String.class);
                List<String> temp = new ArrayList<>();
                for (String str : list){
                    temp.add(str);
                }
                sceneVO.setBookedSeatList(temp);
            }
            sceneVOList.add(sceneVO);
        }
        return new ResponseEntity(sceneVOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) {
        Scene scene = repository.findById(id);
        if (scene!=null){
            return new ResponseEntity(scene,HttpStatus.OK);
        }
        return new ResponseEntity(ReturnVOUtil.notFound(),HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/movies")
    public ResponseEntity getSceneMovie(@PathVariable("id") Integer id) {
        Scene scene = repository.findById(id);
        if (scene == null){
            return new ResponseEntity(null,HttpStatus.OK);
        }
        List<Movie> movieList = movieRepository.findMovieByName(scene.getMovieName());
        return new ResponseEntity(movieList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody Scene scene) {
        Movie movie = movieRepository.findById(scene.getMovieId());
        if (movie == null){
            return new ResponseEntity(ReturnVOUtil.createFail("目前该电影还没出生哦！所以暂时不能为此电影建立场次"),HttpStatus.NOT_FOUND);
        }
        if (movie.getName().equals(scene.getMovieName())){
            int i = repository.insert(scene);
            if (i>0){
                return new ResponseEntity(ReturnVOUtil.success(),HttpStatus.OK);
            }
        }
        return new ResponseEntity(ReturnVOUtil.createFail("创建失败"),HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @RequestBody Scene scene) {
        Scene scene1 = repository.findById(id);
        if (scene1 == null){
            return new ResponseEntity(ReturnVOUtil.notFound(),HttpStatus.NOT_FOUND);
        }
        scene.setId(id);
        int i = repository.update(scene);
        if (i>0){
            return new ResponseEntity(ReturnVOUtil.success(),HttpStatus.OK);
        }
        return new ResponseEntity(ReturnVOUtil.createFail("修改失败"),HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        Scene scene = repository.findById(id);
        if (scene == null){
            return new ResponseEntity(ReturnVOUtil.notFound(),HttpStatus.NOT_FOUND);
        }
        int i = repository.delete(id);
        if (i>0){
            return new ResponseEntity(ReturnVOUtil.success(),HttpStatus.OK);
        }
        return new ResponseEntity(ReturnVOUtil.createFail("删除失败"),HttpStatus.BAD_REQUEST);
    }
}
