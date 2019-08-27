package com.xiao.amovie.service.impl;

import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.repository.CategoryRepository;
import com.xiao.amovie.repository.MovieRepository;
import com.xiao.amovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
