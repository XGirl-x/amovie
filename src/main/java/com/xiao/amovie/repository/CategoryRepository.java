package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiao
 */
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

    @Insert("insert into movie_category(category_id,movie_id) values(#{categoryId},#{movieId})")
    int insertCategories(Integer categoryId,Integer movieId);

    @Delete("delete from movie_category where movie_Id=#{movieId}")
    int deleteCategories(Integer movieId);

    @Update("update movie_category set category_id = #{categoryId} where movie_id = #{movieId}")
    int updateCategories(Integer categoryId, Integer movieId);
}
