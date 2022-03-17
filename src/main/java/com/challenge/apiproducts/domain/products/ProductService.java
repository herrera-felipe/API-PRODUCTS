package com.challenge.apiproducts.domain.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductGateway productGateway;

    @Autowired
    public ProductService(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public ProductModel save(ProductModel product) {
        return productGateway.save(product);
    }

    public List<ProductModel> findAll() {
        return productGateway.findAll();
    }

    public ProductModel findByName(String name) {
        return productGateway.findByName(name);
    }

    public ProductModel update(long id, ProductModel product) {
        return productGateway.update(id, product);
    }

    public void delete(long id) {
        productGateway.delete(id);
    }
}
