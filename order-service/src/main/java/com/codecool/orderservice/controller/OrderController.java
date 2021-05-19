package com.codecool.orderservice.controller;

import com.codecool.orderservice.model.Order;
import com.codecool.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path={"", "/"})
    public Order placeOrder(@RequestBody Order order){
        Order savedOrder = orderService.saveOrder(order);
        return savedOrder;

    }
}
