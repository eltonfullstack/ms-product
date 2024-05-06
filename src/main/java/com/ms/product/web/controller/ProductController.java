package com.ms.product.web.controller;

import com.ms.product.entity.Product;
import com.ms.product.service.ProductService;
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
    public ResponseEntity<Product> save(@RequestBody Product product) {
        /* Product createdProduct = productService.save(product); **/
        Product createdProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.status((HttpStatus.OK)).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.status((HttpStatus.OK)).body(product);
    }
}
