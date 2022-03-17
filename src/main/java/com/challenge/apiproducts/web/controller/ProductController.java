package com.challenge.apiproducts.web.controller;

import com.challenge.apiproducts.domain.products.ProductModel;
import com.challenge.apiproducts.domain.products.ProductService;
import com.challenge.apiproducts.web.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductModel> modelList = productService.findAll();
        List<ProductDTO> dtoList = toDtoList(modelList);
        return ResponseEntity.ok().body(dtoList);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO dto) {
        ProductModel productModel = productService.save(toModel(dto));
        ProductDTO productDTO = toDto(productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        ProductModel productModel = productService.update(id, toModel(dto));
        ProductDTO productDTO = toDto(productModel);
        return ResponseEntity.ok().body(productDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    private List<ProductDTO> toDtoList(List<ProductModel> modelList) {
        return modelList.stream().map(this::toDto).collect(toList());
    }


    private ProductModel toModel(ProductDTO dto) {
        return ProductModel.builder()
                .id(dto.getId())
                .SKU(dto.getSKU())
                .code(dto.getCode())
                .name(dto.getName())
                .description(dto.getDescription())
                .picture(dto.getPicture())
                .price(dto.getPrice())
                .currency(dto.getCurrency())
                .build();
    }


    private ProductDTO toDto(ProductModel model) {
        return ProductDTO.builder()
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
}
