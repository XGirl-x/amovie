package com.xiao.amovie.controller;

import com.xiao.amovie.entity.Order;
import com.xiao.amovie.entity.Scene;
import com.xiao.amovie.entity.User;
import com.xiao.amovie.enums.OrderStatus;
import com.xiao.amovie.from.MovieScore;
import com.xiao.amovie.from.OrderForm;
import com.xiao.amovie.from.ResultForm;
import com.xiao.amovie.from.TicketForm;
import com.xiao.amovie.repository.OrderRepository;
import com.xiao.amovie.repository.SceneRepository;
import com.xiao.amovie.service.MovieService;
import com.xiao.amovie.service.OrderService;
import com.xiao.amovie.utils.ResultVOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author xiao
 * @date 2019-08-30 16:41
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SceneRepository sceneRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public String book1(@PathVariable("id") Integer id, Model model) {
        MovieScore movieScore = movieService.findByMovieId(id);
        List<Scene> sceneList = sceneRepository.findByMovieName(movieScore.getName());
        model.addAttribute("movie",movieScore);
        model.addAttribute("sceneList",sceneList);
        return "book1";
    }

    @GetMapping("/book2")    // 场次id
    public String book2(Model model, Scene scene) {
        if (scene != null) {
            MovieScore movieScore = movieService.findByMovieId(scene.getMovieId());
            Scene scene1 = sceneRepository.findById(scene.getId());
            String bookedSeat = scene1.getBookedSeat();
            bookedSeat = bookedSeat.replaceAll(" ","");
            String[] split = {};
            if (!StringUtils.isEmpty(bookedSeat)) {
                split = Arrays.stream(bookedSeat.split(",")).map(s -> String.format("\"%s\"",s)).toArray(String[]::new);
            }
            model.addAttribute("movie",movieScore);
            model.addAttribute("scene",scene1);
            model.addAttribute("bookedSeat",Arrays.toString(split));
            return "book2";
        }
        return null;
    }

    @GetMapping("/book3")
    public String book3(OrderForm orderForm, Model model) {
        if (orderForm != null) {
            model.addAttribute("orderForm",orderForm);
            return "book3-buy";
        }
        return null;
    }

    @PostMapping("/bookFinal")
    @ResponseBody
    public ResultForm bookFinal(@RequestBody Order order, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (order != null) {
            order.setUserId(user.getId());
            order.setStatus(OrderStatus.UNPAID.getCode());
            order.setCreateTime(new Date());
            int i = orderService.saveOrder(order);
            if (i > 0) {
                return ResultVOUtil.success();
            }
        }
        return ResultVOUtil.error("预订失败");
    }

    @GetMapping("/success/{sceneId}/{totalPrice}/{bookedSeat}")
    public String bookSuccess(@PathVariable("sceneId") Integer sceneId,
                              @PathVariable("totalPrice") BigDecimal totalPrice,
                              @PathVariable("bookedSeat") String bookedSeat,
                              Model model) {
        Scene scene = sceneRepository.findById(sceneId);
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("bookedSeat",bookedSeat);
        model.addAttribute("scene",scene);
        return "book-final";
    }

    @GetMapping("/ticket")
    public String getTicket(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        List<Order> orderList = orderRepository.findByUserId(user.getId());
        List<TicketForm> ticketFormList = new ArrayList<>();
        for (Order order : orderList) {
            TicketForm ticketForm = new TicketForm();
            BeanUtils.copyProperties(order,ticketForm);
            Scene scene = sceneRepository.findById(order.getSceneId());
            ticketForm.setMovieName(scene.getMovieName());
            ticketForm.setShowTime(scene.getShowtime());
            ticketFormList.add(ticketForm);
        }
        model.addAttribute("ticketFormList",ticketFormList);
        return "ticket";
    }
}
