package com.xiao.amovie.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiao.amovie.entity.News;
import com.xiao.amovie.exception.CommonException;
import com.xiao.amovie.exception.NotFoundException;
import com.xiao.amovie.repository.NewsRepository;
import com.xiao.amovie.utils.ReturnVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiao
 */
@Controller
@CrossOrigin
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsRepository repository;

    @GetMapping
    public ResponseEntity getNews(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                  @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        Page<News> newsList = PageHelper.startPage(page, size).doSelectPage(() -> repository.getAll());
        return new ResponseEntity(newsList.toPageInfo(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id", required = true) Integer id) {
        News news = repository.findById(id);
        if (news != null) {
            return new ResponseEntity(news, HttpStatus.OK);
        }
        throw new NotFoundException("资源未找到");
    }

    @PostMapping
    public ResponseEntity insert(@RequestParam("title") String title, @RequestParam("content") String content) {
        int i = repository.insert(new News(title, content));
        if (i > 0) {
            return new ResponseEntity(ReturnVOUtil.success(), HttpStatus.OK);
        }
        throw new CommonException("创建失败");
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @RequestParam("content") String content) {
        News news = repository.findById(id);
        if (news == null) {
            throw new NotFoundException("资源未找到");
        }
        news.setContent(content);
        int i = repository.update(news);
        if (i > 0) {
            return new ResponseEntity(ReturnVOUtil.success(), HttpStatus.OK);
        }
        throw new CommonException("修改失败");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        News news = repository.findById(id);
        if (news == null) {
            throw new NotFoundException("资源未找到");
        }
        int i = repository.delete(id);
        if (i > 0) {
            return new ResponseEntity(ReturnVOUtil.success(), HttpStatus.OK);
        }
        throw new CommonException("删除失败");
    }
}
