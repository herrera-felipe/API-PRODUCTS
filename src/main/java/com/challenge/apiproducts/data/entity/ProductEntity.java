package com.challenge.apiproducts.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long SKU;

    @Column(nullable = false, unique = true)
    private Long code;

    @Column(nullable = false)
    private String name;

    private String description;

    private String picture;

    private Double price;

    private String currency;
}
