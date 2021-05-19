package com.codecool.orderservice.repository;

import com.codecool.orderservice.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
