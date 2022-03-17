package com.challenge.apiproducts.domain.products;

import java.util.List;

public interface ProductGateway {

    ProductModel save(ProductModel product);
    List<ProductModel> findAll();
    ProductModel findByName(String name);
    ProductModel update(Long id, ProductModel product);
    void delete(Long id);
}
