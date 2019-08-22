package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {

    @Insert("insert into category(id,name) values(#{id},#{name})")
    int insert(Category category);

    @Delete("delete from category where id=#{id}")
    int delete(Integer id);

    @Update("update category set name=#{name} where id=#{id}")
    int update(Category category);

    @Select("select * from category where id=#{id}")
    Category findById(Integer id);

    @Select("select * from category")
    List<Category> getAll();
}
