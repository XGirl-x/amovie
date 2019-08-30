package com.xiao.amovie.service.impl;

import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.from.MovieScore;
import com.xiao.amovie.repository.CategoryRepository;
import com.xiao.amovie.repository.MovieRepository;
import com.xiao.amovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiao
 */
@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public int insert(Movie movie, Integer[] categoryIds) {
        int i = repository.insert(movie);
        if (i > 0) {
            Integer movieId = movie.getId();
            if (categoryIds == null) {
                return i;
            }
            for (int categoryId : categoryIds) {
                categoryRepository.insertCategories(categoryId, movieId);
            }
        }
        return i;
    }

    @Override
    public int update(Movie movie, Integer[] categoryIds) {
        int i = repository.update(movie);
        if (i > 0) {
            Integer movieId = movie.getId();
            for (int categoryId : categoryIds) {
                categoryRepository.updateCategories(categoryId, movieId);
            }
        }
        return i;
    }

    @Override
    public int delete(Integer id) {
        int i = repository.delete(id);
        if (i > 0) {
            categoryRepository.deleteCategories(id);
        }
        return i;
    }

    @Override
    public List<MovieScore> findMovieScore() {
        List<MovieScore> movieScores = repository.findMovieScore();
        for (MovieScore movieScore : movieScores) {
            if (movieScore.getNum() == null) {
                movieScore.setNum(0);
            }
            if (movieScore.getAvg() == null) {
                movieScore.setAvg(0.0);
            }
        }
        return movieScores;
    }

    @Override
    public List<MovieScore> findMovieScoreSort() {
        List<MovieScore> movieScoreSort = repository.findMovieScoreSort();
        for (MovieScore movieScore : movieScoreSort) {
            if (movieScore.getNum() == null) {
                movieScore.setNum(0);
            }
            if (movieScore.getAvg() == null) {
                movieScore.setAvg(0.0);
            }
        }
        return movieScoreSort;
    }

    @Override
    public MovieScore findByMovieId(Integer movieId) {
        MovieScore movieScore = repository.findByMovieId(movieId);
        if (movieScore.getNum() == null) {
            movieScore.setNum(0);
        }
        if (movieScore.getAvg() == null) {
            movieScore.setAvg(0.0);
        }
        return movieScore;
    }

    @Override
    public List<MovieScore> findByName(String name) {
        List<MovieScore> movieScoreList = repository.findByName(name);
        for (MovieScore movieScore : movieScoreList) {
            if (movieScore.getNum() == null) {
                movieScore.setNum(0);
            }
            if (movieScore.getAvg() == null) {
                movieScore.setAvg(0.0);
            }
        }
        return movieScoreList;
    }

    @Override
    public List<MovieScore> findByActors(String actor) {
        List<MovieScore> movieScoreList = repository.findByActors(actor);
        for (MovieScore movieScore : movieScoreList) {
            if (movieScore.getNum() == null) {
                movieScore.setNum(0);
            }
            if (movieScore.getAvg() == null) {
                movieScore.setAvg(0.0);
            }
        }
        return movieScoreList;
    }

    @Override
    public List<MovieScore> findByDirectors(String directors) {
        List<MovieScore> movieScoreList = repository.findByDirectors(directors);
        for (MovieScore movieScore : movieScoreList) {
            if (movieScore.getNum() == null) {
                movieScore.setNum(0);
            }
            if (movieScore.getAvg() == null) {
                movieScore.setAvg(0.0);
            }
        }
        return movieScoreList;
    }

    @Override
    public List<MovieScore> findByCountry(String country) {
        List<MovieScore> movieScoreList = repository.findByCountry(country);
        for (MovieScore movieScore : movieScoreList) {
            if (movieScore.getNum() == null) {
                movieScore.setNum(0);
            }
            if (movieScore.getAvg() == null) {
                movieScore.setAvg(0.0);
            }
        }
        return movieScoreList;
    }


}
