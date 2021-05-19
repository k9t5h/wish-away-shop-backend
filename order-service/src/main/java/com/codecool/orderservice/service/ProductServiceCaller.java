package com.codecool.orderservice.service;

import com.codecool.orderservice.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceCaller {

    private RestTemplate restTemplate;

    @Value("${product-service.url}")
    private String productUrl;

    @Autowired
    public ProductServiceCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductDTO> getProducts(List<Long> productIds){
        return Arrays.asList(restTemplate.postForEntity(productUrl + "/list", productIds, ProductDTO[].class).getBody());
    }
}
