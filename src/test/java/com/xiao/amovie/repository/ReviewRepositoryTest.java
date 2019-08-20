package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Review;
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
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository repository;

    @Test
    public void insert() {
        int i = repository.insert(Review.builder()
                .content("超赞的电影")
                .movieId(2)
                .userId(6)
                .build());
        Assert.assertEquals(1,i);
    }

    @Test
    public void delete() {
        int i = repository.delete(2);
        Assert.assertEquals(1,i);
    }

    @Test
    public void update() {
        Review review = repository.findById(1);
        review.setContent("刺激大片");
        int i = repository.update(review);
        Assert.assertEquals(1,i);
    }

    @Test
    public void getAll() {
        List<Review> reviewList = repository.getAll();
        Assert.assertNotNull(reviewList);
    }
}