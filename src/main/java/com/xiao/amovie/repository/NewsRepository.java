package com.xiao.amovie.repository;

import com.xiao.amovie.entity.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiao
 */
@Repository
public interface NewsRepository {

    @Insert("insert into `news` (content,create_time) values(#{content},#{createTime})")
    int insert(News news);

    @Delete("delete from news where id=#{id}")
    int delete(Integer id);

    @Update("update news set content=#{content} where id=#{id}")
    int update(News news);

    @Select("select * from news where id=#{id}")
    News findById(Integer id);

    @Select("select * from news")
    List<News> getAll();
}
