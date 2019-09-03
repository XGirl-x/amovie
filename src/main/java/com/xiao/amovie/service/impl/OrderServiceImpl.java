package com.xiao.amovie.service.impl;

import com.xiao.amovie.entity.Order;
import com.xiao.amovie.entity.Scene;
import com.xiao.amovie.repository.OrderRepository;
import com.xiao.amovie.repository.SceneRepository;
import com.xiao.amovie.service.OrderService;
import com.xiao.amovie.utils.Json;
import com.xiao.amovie.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author xiao
 * @date 2019-09-02 22:12
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SceneRepository sceneRepository;

    @Override
    public int saveOrder(Order order) {
        int i = orderRepository.insert(order);
        if (i > 0) {
            Scene scene = sceneRepository.findById(order.getSceneId());

            //场次原来已预订的座位
            String bookedSeat = scene.getBookedSeat();
            if (bookedSeat == null) {
                scene.setBookedSeat(order.getBookedSeat());
            }
            else {
                StringBuffer sb =new StringBuffer();
                sb.append(bookedSeat).append(",").append(order.getBookedSeat());
                scene.setBookedSeat(sb.toString());
            }
            return sceneRepository.update(scene);
        }
        return 0;
    }
}
