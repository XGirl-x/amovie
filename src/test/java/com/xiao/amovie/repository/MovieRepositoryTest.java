package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.enums.Status;
import com.xiao.amovie.from.MovieScore;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

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
                        Status.NOTSHOWN.getCode(),
                        "《速度与激情》是一部由罗伯·科恩执导的动作犯罪类电影。是环球影业发行的《速度与激情》系列电影的第一部。保罗·沃克、范·迪塞尔、乔丹娜·布鲁斯特和米歇尔·罗德里格兹等...",
                        "images/poster/movie1.jpg",
                        "美国"));
        Assert.assertEquals(1, i);
    }

    @Test
    public void delete() {
        int i = repository.delete(1);
        Assert.assertEquals(1, i);
    }

    @Test
    public void update() {
        Movie movie = repository.findById(2);
        System.out.println(movie);
        movie.setName("速度与激情4");
        int i = repository.update(movie);
        Assert.assertEquals(1, i);
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
    public void findByName() {
        List<MovieScore> movieList = repository.findByName("速度与激情");
        System.out.println(movieList);
        Assert.assertNotNull(movieList);
    }

    @Test
    public void findByDirectors() {
        List<MovieScore> movieList = repository.findByDirectors("饺子");
        System.out.println(movieList);
    }

    @Test
    public void findByActors() {
        List<MovieScore> movieList = repository.findByActors("范");
        System.out.println(movieList);
    }

    @Test
    public void findByCountry() {
        List<MovieScore> movieList = repository.findByCountry("中国");
        System.out.println(movieList);
    }

    @Test
    public void findByCategoryId() {
        List<Movie> movieList = repository.findByCategoryId(1);
        System.out.println(movieList);
    }

    @Test
    public void findMovieScore() {
        List<MovieScore> movieScore = repository.findMovieScore();
        System.out.println(movieScore);
    }

    @Test
    public void findByMovieId() {
        MovieScore movieScore = repository.findByMovieId(2);
        System.out.println(movieScore);
    }

}