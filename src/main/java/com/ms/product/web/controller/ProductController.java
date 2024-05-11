package com.ms.product.web.controller;

import com.ms.product.entity.Product;
import com.ms.product.service.ProductService;
import com.ms.product.web.dto.ProductCreateDto;
import com.ms.product.web.dto.ProductResponseDto;
import com.ms.product.web.dto.ProductUpdateDto;
import com.ms.product.web.dto.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@Valid @RequestBody ProductCreateDto productDto) {
        Product createdProduct = productService.save(ProductMapper.toProduct(productDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toProductDto(createdProduct));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.status((HttpStatus.OK)).body(ProductMapper.toListDto(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.status((HttpStatus.OK)).body(ProductMapper.toProductDto(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable("id") Long id, @Valid @RequestBody ProductUpdateDto productDto) {
        Product updatedProduct = productService.update(
                id, productDto.getName(),
                productDto.getImage(),
                productDto.getDescription(),
                productDto.getPrice());

        return ResponseEntity.status((HttpStatus.OK)).body(ProductMapper.toProductDto(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return ResponseEntity.status((HttpStatus.OK)).build();
    }
}
