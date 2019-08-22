package com.xiao.amovie.service.impl;

import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.repository.MovieRepository;
import com.xiao.amovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository repository;

    @Override
    public int insert(Movie movie) {
        return 0;
    }

    @Override
    public int update(Movie movie) {
        return 0;
    }
}
