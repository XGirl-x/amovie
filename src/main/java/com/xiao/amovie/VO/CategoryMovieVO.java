package com.xiao.amovie.VO;

import com.xiao.amovie.entity.Movie;
import lombok.Data;

import java.util.List;

/**
 * 目录包含电影
 */
@Data
public class CategoryMovieVO {

    private String id;

    private String name;

    private List<Movie> movieList;
}
