package com.xiao.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private Integer id;

    private String name;

    public Category(String name) {
        this.name = name;
    }
}
