package com.xiao.amovie.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.amovie.entity.Score;
import com.xiao.amovie.entity.User;
import com.xiao.amovie.from.MovieScore;
import com.xiao.amovie.from.ResultForm;
import com.xiao.amovie.repository.ScoreRepository;
import com.xiao.amovie.service.MovieService;
import com.xiao.amovie.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author xiao
 * @date 2019-08-29 15:01
 */
@Controller
@RequestMapping("/rate")
public class ScoreController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping
    public String getAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                         @RequestParam(value = "size", required = false, defaultValue = "6") Integer size,
                         Model model) {
        PageInfo<MovieScore> selectPageInfo = PageHelper.startPage(page, size).doSelectPageInfo(() -> movieService.findMovieScoreSort());
        model.addAttribute("movieScore",selectPageInfo);
        return "rate";
    }

    @PostMapping
    @ResponseBody
    public ResultForm insert(@RequestBody Score score,HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResultVOUtil.error("请登录");
        }
        Score score1 = scoreRepository.findByMovieIdAndUserId(score.getMovieId(), user.getId());
        if (score1 != null) {
            return ResultVOUtil.error("您已经评分无需再次评分");
        }
        score.setUserId(user.getId());
        int i = scoreRepository.insert(score);
        if (i > 0) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("评分失败！");

    }

}
