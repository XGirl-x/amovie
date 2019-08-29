package com.xiao.amovie.controller;

import com.xiao.amovie.entity.Review;
import com.xiao.amovie.from.ResultForm;
import com.xiao.amovie.repository.ReviewRepository;
import com.xiao.amovie.utils.ResultVOUtil;
import com.xiao.amovie.utils.ReturnVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiao
 * @date 2019-08-28 09:27
 */
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping
    public ResultForm comments(@RequestBody Review review) {
        int i = reviewRepository.insert(review);
        if (i>0) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("操作失败");
    }


}
