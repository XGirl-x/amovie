package com.xiao.amovie.from;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SceneForm {

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
