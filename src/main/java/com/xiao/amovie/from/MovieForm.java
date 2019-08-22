package com.xiao.amovie.from;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xiao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieForm {

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

    private Integer[] categoryIds;


    public MovieForm(String name, String duration, String directors, String actors, Date releaseDate, Integer status, String plot, String poster, String country) {
        this.name = name;
        this.duration = duration;
        this.directors = directors;
        this.actors = actors;
        this.releaseDate = releaseDate;
        this.status = status;
        this.plot = plot;
        this.poster = poster;
        this.country = country;
    }
}
