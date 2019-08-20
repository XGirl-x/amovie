package com.xiao.amovie.repository;

import com.xiao.amovie.VO.CategoryMovieVO;
import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.enums.Status;
import com.xiao.amovie.utils.Json;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository repository;

    @Test
    public void insert() {
        int i = repository.insert(
                new Movie("速度与激情4",
                        "50:33",
                        "F·加里·格雷",
                        "范·迪赛尔",
                        new Date(),
                        "[1,2]",
                        Status.NOTSHOWN.getCode(),
                        "《速度与激情》是一部由罗伯·科恩执导的动作犯罪类电影。是环球影业发行的《速度与激情》系列电影的第一部。保罗·沃克、范·迪塞尔、乔丹娜·布鲁斯特和米歇尔·罗德里格兹等...",
                        "images/poster/movie1.jpg",
                        "美国"));
        Assert.assertEquals(1,i);
    }

    @Test
    public void delete() {
        int i = repository.delete(1);
        Assert.assertEquals(1,i);
    }

    @Test
    public void update() {
        Movie movie = repository.findById(2);
        movie.setName("速度与激情2");
        int i = repository.update(movie);
        Assert.assertEquals(1,i);
    }

    @Test
    public void getAll() {
        List<Movie> movieList = repository.getAll();
        System.out.println(movieList);
        Assert.assertNotNull(movieList);
    }

    @Test
    public void findByStatus() {
        List<Movie> movieList = repository.findByStatus(Status.RELEASED.getCode());
        System.out.println(movieList);
        Assert.assertNotNull(movieList);
    }

    @Test
    public void list() {
        //查询所有上映电影
        List<Movie> movieList = repository.findByStatus(Status.RELEASED.getCode());

        //查询类目
        List<String> categoryIdList = new ArrayList<>();
        for (Movie movie:movieList){
            categoryIdList.add(movie.getCategoryId());
        }

        //数据拼装
        List<CategoryMovieVO> categoryMovieVOList = new ArrayList<>();
    }

    @Test
    public void findCategoryId() {
        Movie movie = repository.findById(2);
        String ids = movie.getCategoryId();
        List<Integer> list = Json.parseArray(ids,Integer.class);
    }

    @Test
    public void findByName() {
        List<Movie> movieList = repository.findByName("%速度与激情%");
        System.out.println(movieList);
        Assert.assertNotNull(movieList);
    }
}