package com.xiao.amovie.from;

import com.xiao.amovie.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author xiao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieScore {

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

    private List<Category> categories;

    /**
     * 评分
     */
    private Double avg;

    /**
     * 评分人数
     */
    private Integer num;

    /**
     * 排序后序号
     */
    private Integer rownum;

}
