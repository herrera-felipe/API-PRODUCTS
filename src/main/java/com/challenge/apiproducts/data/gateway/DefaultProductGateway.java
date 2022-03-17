package com.challenge.apiproducts.data.gateway;

import com.challenge.apiproducts.data.entity.ProductEntity;
import com.challenge.apiproducts.data.repository.ProductRepository;
import com.challenge.apiproducts.domain.products.ProductGateway;
import com.challenge.apiproducts.domain.products.ProductModel;
import com.challenge.apiproducts.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.*;

@Component
public class DefaultProductGateway implements ProductGateway {

    private final ProductRepository productRepository;

    @Autowired
    public DefaultProductGateway(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductModel save(ProductModel product) {
        return toModel(productRepository.save(toEntity(product)));
    }

    @Override
    public List<ProductModel> findAll() {
        List<ProductEntity> entities = productRepository.findAll();
        return entities.stream().map(this::toModel).collect(toList());
    }

    @Override
    public ProductModel findByName(String name) {
        ProductEntity entity = productRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("Product not found")
        );
        return toModel(entity);
    }

    @Override
    public ProductModel update(Long id, ProductModel model) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found")
        );
        ProductEntity updatedEntity = updateProduct(entity, model);
        return toModel(productRepository.save(updatedEntity));
    }

    @Override
    public void delete(Long id) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found")
        );
        productRepository.delete(entity);
    }


    private ProductEntity toEntity(ProductModel model) {
        return ProductEntity.builder()
                .id(model.getId())
                .SKU(model.getSKU())
                .code(model.getCode())
                .name(model.getName())
                .description(model.getDescription())
                .picture(model.getPicture())
                .price(model.getPrice())
                .currency(model.getCurrency())
                .build();
    }


    private ProductModel toModel(ProductEntity entity) {
        return ProductModel.builder()
                .id(entity.getId())
                .SKU(entity.getSKU())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .picture(entity.getPicture())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .build();
    }


    private ProductEntity updateProduct(ProductEntity entity, ProductModel model) {
        entity.setSKU(model.getSKU());
        entity.setCode(model.getCode());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setPicture(model.getPicture());
        entity.setPrice(model.getPrice());
        entity.setCurrency(model.getCurrency());
        return entity;
    }
}
