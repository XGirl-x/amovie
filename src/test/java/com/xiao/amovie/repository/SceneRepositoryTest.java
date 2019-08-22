package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Scene;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SceneRepositoryTest {

    @Autowired
    private SceneRepository repository;

    @Test
    public void insert() {
        int i = repository.insert(Scene.builder()
                .movieId(2)
                .movieName("速度与激情2")
                .price(new BigDecimal(35.4))
                .showtime("")
                .bookedSeat("[G2,G3]")
                .build());
        Assert.assertEquals(1, i);
    }

    @Test
    public void delete() {
        int i = repository.delete(2);
        Assert.assertEquals(1, i);
    }

    @Test
    public void update() {
        Scene scene = repository.findById(1);
        scene.setPrice(new BigDecimal(33.3));
        int i = repository.update(scene);
        Assert.assertEquals(1, i);
    }

    @Test
    public void getAll() {
        List<Scene> sceneList = repository.getAll();
        Assert.assertNotNull(sceneList);
    }

    @Test
    public void findByMovieName() {
        List<Scene> sceneList = repository.findByMovieName("速度与激情");
        System.out.println(sceneList);
    }
}