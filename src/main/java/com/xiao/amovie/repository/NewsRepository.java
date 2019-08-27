package com.xiao.amovie.repository;

import com.xiao.amovie.entity.News;
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
public interface NewsRepository {

    @Insert("insert into `news` (content,create_time,title) values(#{content},#{createTime},#{title})")
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
