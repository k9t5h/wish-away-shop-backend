package com.codecool.product.controller;

import com.codecool.product.model.Category;
import com.codecool.product.model.Product;
import com.codecool.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String category) {
        return category == null ? productService.findAllProducts() : productService.findProductsByCategory(category);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        productService.deleteProductById(id);
        return product;
    }

    @PutMapping("/{id}/sold")
    public void setProductSold(@PathVariable Long id) {
        productService.updateSoldProperty(id);
    }

    @GetMapping("/categories")
    public Category[] getAllCategories() {
        return Category.values();
    }

    @PostMapping("/list")
    public List<Product> getProductsByIdList(@RequestBody List<Long> productIDs) {
        return productService.findProductsByIdList(productIDs);
    }

}
