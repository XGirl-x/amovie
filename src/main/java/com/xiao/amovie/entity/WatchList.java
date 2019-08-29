package com.xiao.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author xiao
 * @date 2019-08-28 15:55
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchList {

    private Integer id;

    private Integer userId;

    private Integer movieId;

    private Date createTime;
}
