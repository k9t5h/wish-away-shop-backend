package com.codecool.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private long id;
    private String name;
    private String category;
    private String imageUrl;
    private String description;
    private int price;
    private boolean sold;
}
