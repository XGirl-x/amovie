package com.xiao.amovie.VO;

import com.xiao.amovie.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieVO {

    private Integer id;

    /*电影名称*/
    private String name;

    /*时长*/
    private String duration;

    /*导演*/
    private String directors;

    /*演员*/
    private String actors;

    /*上映日期*/
    private Date releaseDate;

    /*类别id*/
    private String categoryId;

    /**
     * 上映状态
     * {@link com.xiao.amovie.enums.Status}
     */
    private Integer status;

    /*剧情*/
    private String plot;

    /*海报*/
    private String poster;

    /*国家*/
    private String country;

    private List<Category> list;

    public MovieVO(String name, String duration, String directors, String actors, Date releaseDate, String categoryId, Integer status, String plot, String poster, String country) {
        this.name = name;
        this.duration = duration;
        this.directors = directors;
        this.actors = actors;
        this.releaseDate = releaseDate;
            //List<String> categoryIds = Arrays.stream(categoryId).map(e->new String(e)).collect(Collectors.toList());
        this.categoryId = categoryId;
        this.status = status;
        this.plot = plot;
        this.poster = poster;
        this.country = country;
    }
}
