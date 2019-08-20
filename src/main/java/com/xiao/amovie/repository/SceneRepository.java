package com.xiao.amovie.repository;


import com.xiao.amovie.entity.Scene;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SceneRepository {

    @Insert("insert into `scene` (movie_id,movie_name,price,showtime,booked_seat) values(#{movieId},#{movieName},#{price},#{showtime},#{bookedSeat})")
    int insert(Scene scene);

    @Delete("delete from `scene` where id=#{id}")
    int delete(Integer id);

    @Update("update `scene` set movie_id=#{movieId},movie_name=#{movieName},price=#{price},showtime=#{showtime},booked_seat=#{bookedSeat}")
    int update(Scene scene);

    @Select("select * from `scene` where id=#{id}")
    @Results({
            @Result(property = "movieId",column = "movie_id"),
            @Result(property = "movieName",column = "movie_name"),
            @Result(property = "bookedSeat",column = "booked_seat")
    })
    Scene findById(Integer id);

    @Select("select * from scene")
    @Results({
            @Result(property = "movieId",column = "movie_id"),
            @Result(property = "movieName",column = "movie_name"),
            @Result(property = "bookedSeat",column = "booked_seat")
    })
    List<Scene> getAll();
}
