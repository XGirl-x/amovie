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

    @Update("update movie set name=#{name},duration=#{duration},directors=#{directors},actors=#{actors},release_date=#{releaseDate},category_id=#{categoryId},status=#{status},plot=#{plot},poster=#{poster},country=#{country} where id=#{id}")
    int update(Movie movie);

    @Select("select * from movie where id=#{id}")
    @Results({
            @Result(property = "releaseDate",column = "release_date"),
            @Result(property = "categoryId",column = "category_id")
    })
    Movie findById(Integer id);

    @Select("select * from movie")
    @Results({
            @Result(property = "releaseDate",column = "release_date"),
            @Result(property = "categoryId",column = "category_id")
    })
    List<Movie> getAll();

    @Select("select * from movie where status=#{status}")
    @Results({
            @Result(property = "releaseDate",column = "release_date"),
            @Result(property = "categoryId",column = "category_id")
    })
    List<Movie> findByStatus(Integer status);

    /**
     * 通过模糊查询
     */
    @Select("select * from movie where name like #{name}")
    List<Movie> findByName(String name);


}
