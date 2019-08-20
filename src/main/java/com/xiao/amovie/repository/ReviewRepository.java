package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Review;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository {

    @Insert("insert into review(content,movie_id,user_id,create_time) values(#{content},#{movieId},#{userId},#{createTime})")
    int insert(Review review);

    @Delete("delete from review where id=#{id}")
    int delete(Integer id);

    @Update("update review set content=#{content},movie_id=#{movieId},user_id=#{userId}")
    int update(Review review);

    @Select("select * from review where id=#{id}")
    @Results({
            @Result(property = "movieId",column = "movie_id"),
            @Result(property = "userId",column = "user_id")
    })
    Review findById(Integer id);

    @Select("select * from review")
    @Results({
            @Result(property = "movieId",column = "movie_id"),
            @Result(property = "userId",column = "user_id")
    })
    List<Review> getAll();
}