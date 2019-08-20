package com.xiao.amovie.repository;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.xiao.amovie.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {

    @Insert("insert into `order` (status,user_id,scene_id,ticket_num,total_price,booked_seat) values(#{status},#{userId},#{sceneId},#{ticketNum},#{totalPrice},#{bookedSeat})")
    int insert(Order order);

    @Delete("delete from `order` where order_id=#{orderId}")
    int delete(Integer orderId);

    @Update("update `order` set status=#{status},user_id=#{userId},scene_id=#{sceneId},ticket_num=#{ticketNum},total_price=#{totalPrice},booked_seat=#{bookedSeat}")
    int update(Order order);

    @Select("select * from `order` where order_id=#{orderId}")
    @Results({
            @Result(property = "orderId",column = "order_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "sceneId",column = "scene_id"),
            @Result(property = "ticketNum",column = "ticket_num"),
            @Result(property = "totalPrice",column = "total_price"),
            @Result(property = "bookedSeat",column = "booked_seat")
    })
    Order findById(Integer orderId);

    @Select("select * from `order`")
    @Results({
            @Result(property = "orderId",column = "order_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "sceneId",column = "scene_id"),
            @Result(property = "ticketNum",column = "ticket_num"),
            @Result(property = "totalPrice",column = "total_price"),
            @Result(property = "bookedSeat",column = "booked_seat")
    })
    List<Order> getAll();
}
