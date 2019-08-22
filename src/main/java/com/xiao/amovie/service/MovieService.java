package com.xiao.amovie.service;

import com.xiao.amovie.entity.Movie;

/**
 * @author xiao
 */
public interface MovieService {

    int insert(Movie movie,Integer[] categoryIds);

    int update(Movie movie,Integer[] categoryIds);

    int delete(Integer id);
}
