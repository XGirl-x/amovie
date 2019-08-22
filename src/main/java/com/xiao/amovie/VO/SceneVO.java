package com.xiao.amovie.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SceneVO {

    private Integer id;

    private Integer movieId;

    private String movieName;

    private BigDecimal price;

    private Integer seatNum = 166;

    private String showtime;

    /*已预订的座位*/
    private String bookedSeat;

    private List<String> bookedSeatList;
}
