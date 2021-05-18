package com.codecool.cartservice;

import com.codecool.cartservice.model.Cart;
import com.codecool.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@SpringBootApplication
@EnableEurekaClient
public class CartServiceApplication {

	private CartRepository cartRepository;

	@Autowired
	public CartServiceApplication(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner init() {
		return args -> {
			Cart cart = Cart.builder().productIds(new ArrayList<>()).products(new ArrayList<>()).build();
			cartRepository.save(cart);
		};

	}

}
