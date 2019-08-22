package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiao
 */
@Repository
public interface OrderRepository {

    @Insert("insert into `order` (status,user_id,scene_id,ticket_num,total_price,booked_seat) values(#{status},#{userId},#{sceneId},#{ticketNum},#{totalPrice},#{bookedSeat})")
    int insert(Order order);

    @Delete("delete from `order` where order_id=#{orderId}")
    int delete(Integer orderId);

    int update(Order order);

    @Select("select * from `order` where order_id=#{orderId}")
    Order findById(Integer orderId);

    @Select("select * from `order`")
    List<Order> getAll();
}
