package com.xiao.amovie.repository;

import com.xiao.amovie.entity.News;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsRepositoryTest {

    @Autowired
    private NewsRepository repository;

    @Test
    public void insert() {
        int i = repository.insert(News.builder()
                .content("今日资讯")
                .build()
        );
        Assert.assertEquals(1,i);
    }

    @Test
    public void update() {
        News news = repository.findById(1);
        news.setContent("资讯资讯");
        int i = repository.update(news);
        Assert.assertEquals(1,i);
    }

    @Test
    public void getAll() {
        List<News> newsList = repository.getAll();
        Assert.assertNotNull(newsList);
    }
}