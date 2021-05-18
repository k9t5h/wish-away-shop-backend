package com.codecool.product.service;

import com.codecool.product.model.Category;
import com.codecool.product.model.Product;
import com.codecool.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findProductsByCategory(String categoryName) {
        Category category = Category.valueOf(categoryName);
        return productRepository.findAllByCategory(category);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found by id - " + id));
    }

    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public void updateSoldProperty(Long id) {
        Product product = findProductById(id);
        product.setSold(true);
        productRepository.save(product);
    }

}
