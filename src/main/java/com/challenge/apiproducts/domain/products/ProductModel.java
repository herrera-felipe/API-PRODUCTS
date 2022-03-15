package com.challenge.apiproducts.domain.products;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductModel {
    private Long id;
    private Long SKU;
    private Long code;
    private String name;
    private String description;
    private String picture;
    private Double price;
    private String currency;
}
