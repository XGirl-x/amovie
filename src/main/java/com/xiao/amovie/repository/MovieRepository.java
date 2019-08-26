package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Movie;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository {

    @Insert("insert into movie(name,duration,directors,actors,release_date,status,plot,poster,country) values(#{name},#{duration},#{directors},#{actors},#{releaseDate},#{status},#{plot},#{poster},#{country})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(Movie movie);

    @Delete("delete from movie where id=#{id}")
    int delete(Integer id);

    int update(Movie movie);


    Movie findById(Integer id);


    List<Movie> getAll();


    List<Movie> findByStatus(Integer status);

    /**
     * 通过模糊查询
     */
    List<Movie> findByName(String name);

    /**
     * 通过导演查询
     *
     * @param directors
     * @return
     */
    List<Movie> findByDirectors(String directors);

    /**
     * 通过演员查询
     *
     * @param actors
     * @return
     */
    List<Movie> findByActors(String actors);

    /**
     * 通过国家查询
     *
     * @param country
     * @return
     */
    List<Movie> findByCountry(String country);

    List<Movie> findByCategoryId(Integer categoryId);

}
