package com.xiao.amovie.service.impl;

import com.xiao.amovie.entity.Review;
import com.xiao.amovie.entity.User;
import com.xiao.amovie.from.ReviewForm;
import com.xiao.amovie.repository.ReviewRepository;
import com.xiao.amovie.repository.UserRepository;
import com.xiao.amovie.service.ReviewService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiao
 * @date 2019-08-27 21:30
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ReviewForm> findByMovieId(Integer movieId) {
        List<Review> reviewList = repository.findByMovieId(movieId);
        List<ReviewForm> reviewForms = new ArrayList<>();
        for (Review review : reviewList) {
            User user = userRepository.findById(review.getUserId());
            ReviewForm reviewForm = new ReviewForm();
            BeanUtils.copyProperties(review,reviewForm);
            reviewForm.setNickName(user.getNickname());
            reviewForms.add(reviewForm);
        }
        return reviewForms;
    }
}
