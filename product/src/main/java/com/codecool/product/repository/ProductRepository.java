package com.codecool.product.repository;

import com.codecool.product.model.Category;
import com.codecool.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryAndSoldFalse(Category category);
    List<Product> findAllBySoldFalse();

}
