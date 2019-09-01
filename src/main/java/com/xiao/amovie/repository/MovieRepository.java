package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Category;
import com.xiao.amovie.entity.Movie;
import com.xiao.amovie.from.MovieScore;
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

    /**
     * 通过模糊查询
     */
    List<MovieScore> findByName(String name);

    /**
     * 通过导演查询
     *
     * @param directors
     * @return
     */
    List<MovieScore> findByDirectors(String directors);

    /**
     * 通过演员查询
     *
     * @param actors
     * @return
     */
    List<MovieScore> findByActors(String actors);

    /**
     * 通过国家查询
     *
     * @param country
     * @return
     */
    List<MovieScore> findByCountry(String country);

    /**
     * 通过类别
     * @param categoryId
     * @return
     */
    List<Movie> findByCategoryId(Integer categoryId);

    /**
     * 查询评分电影
     * @return
     */
    List<MovieScore> findMovieScore();

    /**
     * 排行榜
     * @return
     */
    List<MovieScore> findMovieScoreSort();

    /**
     * 通过movie id查询他的分类
     * @param movieId id
     * @return list
     */
    List<Category> selectCategoryofMovie(int movieId);

    /**
     * 通过movieId查询评分电影
     * @param id
     * @return
     */
    MovieScore findByMovieId(Integer id);

    List<MovieScore> findByStatus(Integer status);
}
