package com.challenge.apiproducts.web.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@Builder
public class ProductDTO {
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty
    private Long SKU;

    @NotNull
    @NotBlank
    @NotEmpty
    private Integer code;

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    private String description;
    private String picture;

    @NotNull
    @NotBlank
    @NotEmpty
    private Double price;

    private String currency;
}
