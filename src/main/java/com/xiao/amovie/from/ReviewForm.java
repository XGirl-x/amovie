package com.xiao.amovie.from;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xiao
 * @date 2019-08-27 21:35
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewForm {

    private Integer id;

    private String content;

    private Integer movieId;

    private Integer userId;

    private String nickName;

    private Date createTime;
}
