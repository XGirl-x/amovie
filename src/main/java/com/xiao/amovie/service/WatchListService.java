package com.xiao.amovie.service;

import com.github.pagehelper.PageInfo;
import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.entity.WatchList;

import java.util.List;

public interface WatchListService {
    /**
     * 通过userId查询改用户想播放的所有电影
     * @param userId
     * @return
     */
    PageInfo<Movie> getAllByUserId(Integer userId, Integer page, Integer size);
}
