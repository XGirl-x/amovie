package com.xiao.amovie.from;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiao
 * @date 2019-09-03 15:05
 */
@Data
public class TicketForm {
    private Integer orderId;

    /**
     * 支付状态  0未支付1已支付
     * {@link com.xiao.amovie.enums.OrderStatus}
     */
    private Integer status;

    private Integer userId;

    private Integer sceneId;

    /*该用户预订或已购买的票数*/
    private Integer ticketNum;

    /*总价*/
    private BigDecimal totalPrice;

    /*该用户预订的座位*/
    private String bookedSeat;

    /*创建时间*/
    private Date createTime;

    private String movieName;

    private String showTime;
}
