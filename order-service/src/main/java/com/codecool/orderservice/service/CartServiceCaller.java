package com.codecool.orderservice.service;

import com.codecool.orderservice.model.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartServiceCaller {

    private RestTemplate restTemplate;

    @Value("${cart-service.url}")
    private String cartUrl;
    @Value("${product-service.url}")
    private String productUrl;

    @Autowired
    public CartServiceCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CartDTO getCart(){
        return restTemplate.getForEntity(cartUrl, CartDTO.class).getBody();
    }

}