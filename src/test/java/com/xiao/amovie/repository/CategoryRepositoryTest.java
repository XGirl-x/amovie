package com.xiao.amovie.repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiao.amovie.entity.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findById() {
        Category category = categoryRepository.findById(1);
        Assert.assertNotNull(category);
    }

    @Test
    public void insert() {
        int i = categoryRepository.insert(Category.builder()
                .name("科幻")
                .build());
        Assert.assertEquals(1, i);
    }

    @Test
    public void update() {
        Category category = categoryRepository.findById(2);
        category.setName("科幻");
        int i = categoryRepository.update(category);
        Assert.assertEquals(1, i);
    }

    @Test
    public void getAll() {
        /*List<Category> categoryList = categoryRepository.getAll();
        Assert.assertNotNull(categoryList);*/
        Page<Category> page = PageHelper.startPage(2, 1).doSelectPage(() -> categoryRepository.getAll());
        System.out.println(page.toPageInfo());
    }


}