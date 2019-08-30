package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository {

    /**
     * 插入一条评分记录
     * @param score
     * @return
     */
    @Insert("insert into scores(movie_id,score,user_id) values(#{movieId},#{score},#{userId})")
    int insert(Score score);

    /**
     * 获取全部评分
     * @return
     */
    @Select("select * from scores")
    List<Score> getAll();

    /**
     * 通过movieId获取
     * @param movieId
     * @return
     */
    @Select("select * from scores where movie_id=#{movieId} and user_id=#{userId}")
    Score findByMovieIdAndUserId(Integer movieId, Integer userId);

    /**
     * 根据movieId获取评分数目
     * @param movieId
     * @return
     */
    @Select("select count(*) from scores where movie_id=#{movieId}")
    Integer getCountByMovieId(Integer movieId);

    /**
     * 根据movieId获取平均评分
     * @return
     */
    @Select("SELECT AVG(`score`) AS `avg` FROM  `scores` group by `movie_id`")
    Integer getAvg();

    


}
