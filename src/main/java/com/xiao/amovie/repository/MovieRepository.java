package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Movie;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository {

    @Insert("insert into movie(name,duration,directors,actors,release_date,category_id,status,plot,poster,country) values(#{name},#{duration},#{directors},#{actors},#{releaseDate},#{categoryId},#{status},#{plot},#{poster},#{country})")
    int insert(Movie movie);

    @Delete("delete from movie where id=#{id}")
    int delete(Integer id);

    int update(Movie movie);

    @Select("select * from movie where id=#{id}")
    Movie findById(Integer id);

    @Select("select * from movie")
    List<Movie> getAll();

    @Select("select * from movie where status=#{status}")
    List<Movie> findByStatus(Integer status);

    /**
     * 通过模糊查询
     */
    @Select("select * from movie where name like #{name}")
    List<Movie> findByName(String name);

    @Select("select * from movie where category_id like #{categoryId}")
    List<Movie> findByCategoryId(String categoryId);

    @Select("select * from movie where name=#{name}")
    List<Movie> findMovieByName(String name);

}
