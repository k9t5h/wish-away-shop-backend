package com.codecool.orderservice.service;

import com.codecool.orderservice.model.CartDTO;
import com.codecool.orderservice.model.Order;
import com.codecool.orderservice.model.ProductDTO;
import com.codecool.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private CartServiceCaller cartServiceCaller;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartServiceCaller cartServiceCaller) {
        this.orderRepository = orderRepository;
        this.cartServiceCaller = cartServiceCaller;
    }

    public Order saveOrder(Order order) {
        CartDTO cart = cartServiceCaller.getCart();
        order.setProducts(cart.getProducts());
        order.setProductIds(cart.getProducts().stream().map(ProductDTO::getId).collect(Collectors.toList()));
        orderRepository.save(order);
        return order;
    }
}
