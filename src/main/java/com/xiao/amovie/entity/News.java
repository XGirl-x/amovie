package com.xiao.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author xiao
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {

    private Integer id;

    private String content;

    private Date createTime;

    private String title;

    public News(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
