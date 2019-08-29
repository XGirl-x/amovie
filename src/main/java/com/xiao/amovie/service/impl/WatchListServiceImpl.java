package com.xiao.amovie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.entity.WatchList;
import com.xiao.amovie.repository.MovieRepository;
import com.xiao.amovie.repository.WatchListRepository;
import com.xiao.amovie.service.WatchListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiao
 * @date 2019-08-28 17:06
 */
@Service
public class WatchListServiceImpl implements WatchListService {

    @Autowired
    private WatchListRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public PageInfo<Movie> getAllByUserId(Integer userId,Integer page, Integer size) {
        PageInfo<WatchList> selectPage = PageHelper.startPage(page, size).doSelectPageInfo(() -> repository.findByUserId(userId));
        List<Movie> movieList = new ArrayList<>();
        if (selectPage!=null) {
            for (WatchList watchList: selectPage.getList()) {
                Movie movie = movieRepository.findById(watchList.getMovieId());
                movieList.add(movie);
            }
        }
        PageInfo<Movie> p = new PageInfo<>();
        BeanUtils.copyProperties(selectPage,p);
        p.setList(movieList);
        return p;
    }


}
