package com.codecool.cartservice.service;

import com.codecool.cartservice.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
        List<ProductDTO> products = new ArrayList<>();
        for(Long productId: productIds){
            ProductDTO productDTO = restTemplate.getForEntity(productUrl + "/" + productId, ProductDTO.class).getBody();
            products.add(productDTO);
        }
        return products;
    }
}
