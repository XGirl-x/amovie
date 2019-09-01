package com.xiao.amovie.service;

import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.from.MovieScore;

import java.util.List;

/**
 * @author xiao
 */
public interface MovieService {

    int insert(Movie movie, Integer[] categoryIds);

    int update(Movie movie, Integer[] categoryIds);

    int delete(Integer id);

    List<MovieScore> findMovieScore();

    List<MovieScore> findMovieScoreSort();

    /**
     * 通过电影id查询
     * @param movieId
     * @return
     */
    MovieScore findByMovieId(Integer movieId);

    List<MovieScore> findByName(String name);

    List<MovieScore> findByActors(String actor);

    List<MovieScore> findByDirectors(String directors);

    List<MovieScore> findByCountry(String country);

    List<MovieScore> findByStatus(Integer status);


}
