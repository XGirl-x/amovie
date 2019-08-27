package com.xiao.amovie.service;

import com.xiao.amovie.entity.Review;
import com.xiao.amovie.from.ReviewForm;

import java.util.List;

public interface ReviewService {

    List<ReviewForm> findByMovieId(Integer movieId);
}
