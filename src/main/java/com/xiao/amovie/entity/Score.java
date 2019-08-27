package com.xiao.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author xiao
 * @date 2019-08-27 09:16
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    private Integer id;

    private Integer movieId;

    private Integer score;

    private Integer userId;
}
