package com.codecool.cartservice.repository;

import com.codecool.cartservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Long, Cart> {
}
