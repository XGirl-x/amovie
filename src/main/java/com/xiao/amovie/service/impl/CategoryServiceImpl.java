package com.xiao.amovie.service.impl;

import com.xiao.amovie.entity.Category;
import com.xiao.amovie.repository.CategoryRepository;
import com.xiao.amovie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> getAll() {
        return repository.getAll();
    }
}
