package com.xiao.amovie.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.amovie.entity.Category;
import com.xiao.amovie.repository.CategoryRepository;
import com.xiao.amovie.service.CategoryService;
import com.xiao.amovie.utils.ReturnVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategroyController {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity getAll(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                 @RequestParam(value = "size",required = false,defaultValue = "20") Integer size) {
        Page<Category> categoryList = PageHelper.startPage(page, size).doSelectPage(() -> service.getAll());
        return new ResponseEntity(categoryList.toPageInfo(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) {
        Category category = repository.findById(id);
        if (category!=null){
            return new ResponseEntity(category,HttpStatus.OK);
        }
        return new ResponseEntity(ReturnVOUtil.notFound(),HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity insert(@RequestParam("name") String name) {
        int i = repository.insert(new Category(name));
        if (i>0){
            return new ResponseEntity(ReturnVOUtil.success(),HttpStatus.OK);
        }
        return new ResponseEntity(ReturnVOUtil.createFail("创建失败"),HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @RequestParam(value = "name",required = true) String name) {
        Category category = repository.findById(id);
        if (category == null){
            return new ResponseEntity(ReturnVOUtil.notFound(),HttpStatus.NOT_FOUND);
        }
        category.setName(name);
        int i = repository.update(category);
        if (i>0){
            return new ResponseEntity(ReturnVOUtil.success(),HttpStatus.OK);
        }
        return new ResponseEntity(ReturnVOUtil.createFail("修改失败"),HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        Category category = repository.findById(id);
        if (category == null){
            return new ResponseEntity(ReturnVOUtil.notFound(),HttpStatus.NOT_FOUND);
        }
        int i = repository.delete(id);
        if (i>0){
            return new ResponseEntity(ReturnVOUtil.success(),HttpStatus.OK);
        }
        return new ResponseEntity(ReturnVOUtil.createFail("删除失败"),HttpStatus.BAD_REQUEST);
    }


}
