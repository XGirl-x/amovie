package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Review;
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
public interface ReviewRepository {

    @Insert("insert into review(content,movie_id,user_id,create_time) values(#{content},#{movieId},#{userId},#{createTime})")
    int insert(Review review);

    @Delete("delete from review where id=#{id}")
    int delete(Integer id);

    @Update("update review set content=#{content},movie_id=#{movieId},user_id=#{userId} where id=#{id}")
    int update(Review review);

    @Select("select * from review where id=#{id}")
    Review findById(Integer id);

    @Select("select * from review where movie_id=#{movieId}")
    List<Review> findByMovieId(Integer movieId);

    @Select("select * from review")
    List<Review> getAll();
}
