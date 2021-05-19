package com.codecool.orderservice.service;

import com.codecool.orderservice.model.CartDTO;
import com.codecool.orderservice.model.OrderModel;
import com.codecool.orderservice.model.ProductDTO;
import com.codecool.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private CartServiceCaller cartServiceCaller;
    private ProductServiceCaller productServiceCaller;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartServiceCaller cartServiceCaller, ProductServiceCaller productServiceCaller) {
        this.orderRepository = orderRepository;
        this.cartServiceCaller = cartServiceCaller;
        this.productServiceCaller = productServiceCaller;
    }

    public OrderModel saveOrder(OrderModel order) {
        CartDTO cart = cartServiceCaller.getCart();
        order.setProducts(cart.getProducts());
        order.setProductIds(cart.getProducts().stream().map(ProductDTO::getId).collect(Collectors.toList()));
        orderRepository.save(order);
        return order;
    }

    public OrderModel getOderById(long id) {
        OrderModel order = orderRepository.findById(id).orElseThrow(() ->new NoSuchElementException("Order with id " + id + " not found."));
        List<ProductDTO> products = productServiceCaller.getProducts(order.getProductIds());
        order.setProducts(products);
        return order;
    }
}
