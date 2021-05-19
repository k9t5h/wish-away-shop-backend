package com.codecool.orderservice.controller;

import com.codecool.orderservice.model.OrderModel;
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
    public OrderModel placeOrder(@RequestBody OrderModel order){
        OrderModel savedOrder = orderService.saveOrder(order);
        return savedOrder;
    }

    @GetMapping(path = "/{id}")
    public OrderModel getOrderById(@PathVariable(name = "id") long id){
        return orderService.getOderById(id);
    }
}
