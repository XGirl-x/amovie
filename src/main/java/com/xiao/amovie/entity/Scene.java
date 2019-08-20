package com.xiao.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scene {

    private Integer id;

    private Integer movieId;

    private String movieName;

    private BigDecimal price;

    private Integer seatNum = 166;

    private Date showtime;

    /*已预订的座位*/
    private String bookedSeat;
}