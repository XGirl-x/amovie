package com.xiao.amovie.repository;

import com.xiao.amovie.entity.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository {

    @Insert("insert into `news` (content,create_time) values(#{content},#{createTime})")
    int insert(News news);

    @Delete("delete from news where id=#{id}")
    int delete(Integer id);

    @Update("update news set content=#{content}")
    int update(News news);

    @Select("select * from news where id=#{id}")
    @Results({
            @Result(property = "createTime",column = "create_time")
    })
    News findById(Integer id);

    @Select("select * from news")
    @Results({
            @Result(property = "createTime",column = "create_time")
    })
    List<News> getAll();
}
