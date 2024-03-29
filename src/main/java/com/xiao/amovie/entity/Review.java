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
public class Review {

    private Integer id;

    private String content;

    private Integer movieId;

    private Integer userId;

    private Date createTime;
}
