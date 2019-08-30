package com.xiao.amovie.repository;

import com.xiao.amovie.entity.WatchList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchListRepository {

    @Insert("insert into watchlist(user_id,movie_id,create_time) values(#{userId},#{movieId},#{createTime})")
    int insert(WatchList watchList);

    @Select("select * from watchlist")
    List<WatchList> getAll();

    @Select("select * from watchlist where movie_id=#{movieId} and user_id=#{userId}")
    WatchList findByMovieIdAndUserId(Integer movieId,Integer userId);

    @Select("select * from watchlist where user_id=#{userId}")
    List<WatchList> findByUserId(Integer userId);

    @Delete("delete from watchlist where movie_id=#{movieId} and user_id=#{userId}")
    int delete(Integer movieId,Integer userId);
}
