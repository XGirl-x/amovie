package com.xiao.amovie.repository;

import com.xiao.amovie.entity.Order;
import com.xiao.amovie.enums.OrderStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    @Test
    public void insert() {
        int i = repository.insert(Order.builder()
                .status(OrderStatus.PAID.getCode())
                .userId(6)
                .sceneId(1)
                .ticketNum(2)
                .totalPrice(new BigDecimal(52.6))
                .bookedSeat("[A1,A2]")
                .build()
        );
        Assert.assertEquals(1,i);
    }

    @Test
    public void update() {
        Order order = repository.findById(1);
        order.setSceneId(1);
        int i = repository.update(order);
        Assert.assertEquals(1,i);
    }

    @Test
    public void getAll() {
        List<Order> orderList = repository.getAll();
        Assert.assertNotNull(orderList);
    }
}