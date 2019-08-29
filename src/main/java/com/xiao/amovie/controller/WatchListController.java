package com.xiao.amovie.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.entity.User;
import com.xiao.amovie.entity.WatchList;
import com.xiao.amovie.from.ResultForm;
import com.xiao.amovie.repository.WatchListRepository;
import com.xiao.amovie.service.WatchListService;
import com.xiao.amovie.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author xiao
 * @date 2019-08-28 16:03
 */
@Controller
@RequestMapping("/watchlists")
public class WatchListController {

    @Autowired
    private WatchListRepository repository;

    @Autowired
    private WatchListService service;

    @GetMapping
    public String getAllByUserId(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", required = false, defaultValue = "3") Integer size,
                                 Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user!=null) {
            PageInfo<Movie> movieList = service.getAllByUserId(user.getId(), page, size);
            model.addAttribute("movieList", movieList);
            return "watchlist";
        }
        return null;
    }


    @PostMapping
    @ResponseBody
    public ResultForm insertWatch(@RequestBody WatchList watchList,HttpSession session) {
        WatchList movieId = repository.findByMovieId(watchList.getMovieId());
        if (movieId != null){
            return ResultVOUtil.error("该电影已被添加至播放列表无需再次添加");
        }
        User user = (User) session.getAttribute("user");
        watchList.setUserId(user.getId());
        watchList.setCreateTime(new Date());
        int i = repository.insert(watchList);
        if (i > 0) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("添加失败");
    }

    @DeleteMapping
    @ResponseBody
    public ResultForm delete (@RequestBody WatchList watchList) {
        int i = repository.delete(watchList.getMovieId());
        if (i > 0) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("删除失败");
    }

}
