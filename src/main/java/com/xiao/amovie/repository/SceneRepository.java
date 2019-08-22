package com.xiao.amovie.repository;


import com.xiao.amovie.entity.Scene;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiao
 */
@Repository
public interface SceneRepository {

    @Insert("insert into `scene` (movie_id,movie_name,price,showtime,booked_seat) values(#{movieId},#{movieName},#{price},#{showtime},#{bookedSeat})")
    int insert(Scene scene);

    @Delete("delete from `scene` where id=#{id}")
    int delete(Integer id);

    int update(Scene scene);

    @Select("select * from `scene` where id=#{id}")
    Scene findById(Integer id);

    @Select("select * from scene")
    List<Scene> getAll();

    @Select("select * from scene where movie_name=#{movieName}")
    List<Scene> findByMovieName(String movieName);


}
