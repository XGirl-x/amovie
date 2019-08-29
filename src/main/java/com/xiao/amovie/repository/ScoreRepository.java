package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository {

    @Insert("insert into scores(movie_id,score,user_id) values(#{movieId},#{score},#{userId})")
    int insert(Score score);

    @Select("select count(*) from scores where movie_id=#{movieId}")
    int getCountByMovieId(Integer movieId);

    @Select("SELECT AVG(`score`) AS `avg` FROM  `scores` WHERE `movie_id`=#{movieId} ")
    int getAvg(Integer movieId);
}
