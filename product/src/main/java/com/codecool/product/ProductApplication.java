package com.codecool.product;

import com.codecool.product.model.Category;
import com.codecool.product.model.Product;
import com.codecool.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
public class ProductApplication {

    private final ProductRepository productRepository;

    public ProductApplication(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            Product product1 = Product.builder().name("Star Wars LEGO").category(Category.TOYS).description("BRAND NEW Star Wars Millennium Falcon model with 7,500 pieces").price(700).imageUrl("https://icon-library.com/images/no-picture-available-icon/no-picture-available-icon-1.jpg").sold(false).build();
            Product product2 = Product.builder().name("Apple iPhone 8").category(Category.ELECTRONICS).description("UNBOXED Space Grey iPhone 8 64GB").price(350).imageUrl("https://icon-library.com/images/no-picture-available-icon/no-picture-available-icon-1.jpg").sold(false).build();
            Product product3 = Product.builder().name("Vans Old Skool Shoes").category(Category.SPORTS).description("Black/White Size 7").price(60).imageUrl("https://icon-library.com/images/no-picture-available-icon/no-picture-available-icon-1.jpg").sold(false).build();

            productRepository.saveAll(Arrays.asList(product1, product2, product3));
        };
    }

}
