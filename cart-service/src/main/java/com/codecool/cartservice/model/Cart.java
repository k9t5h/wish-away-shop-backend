package com.codecool.cartservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cart {

    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ElementCollection
    private List<Long> productIds;

    @Transient
    private List<ProductDTO> products;
}
