package com.ms.product.web.controller;

import com.ms.product.entity.Product;
import com.ms.product.service.ProductService;
import com.ms.product.web.dto.ProductCreateDto;
import com.ms.product.web.dto.ProductResponseDto;
import com.ms.product.web.dto.ProductUpdateDto;
import com.ms.product.web.dto.mapper.ProductMapper;
import com.ms.product.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "Product API")
@Controller
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Save product", description = "Create a new product", responses = {
            @ApiResponse(responseCode = "201", description = "Product created",
                    content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid product data"),
            @ApiResponse(responseCode = "409", description = "Product already exists",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable entity",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@Valid @RequestBody ProductCreateDto productDto) {
        Product createdProduct = productService.save(ProductMapper.toProduct(productDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toProductDto(createdProduct));
    }

    @Operation(summary = "Find all products", responses = {
            @ApiResponse(responseCode = "200", description = "Products found",
                    content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Products not found")
    })
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.status((HttpStatus.OK)).body(ProductMapper.toListDto(products));
    }

    @Operation(summary = "Find product by id", responses = {
            @ApiResponse(responseCode = "200", description = "Product found",
                    content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.status((HttpStatus.OK)).body(ProductMapper.toProductDto(product));
    }

    @Operation(summary = "Update product by id", responses = {
            @ApiResponse(responseCode = "204", description = "Product updated",
                    content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Products not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable("id") Long id, @Valid @RequestBody ProductUpdateDto productDto) {
        Product updatedProduct = productService.update(
                id, productDto.getName(),
                productDto.getImage(),
                productDto.getDescription(),
                productDto.getPrice());

        return ResponseEntity.status((HttpStatus.OK)).body(ProductMapper.toProductDto(updatedProduct));
    }

    @Operation(summary = "Delete product by id", responses = {
            @ApiResponse(responseCode = "204", description = "Product deleted",
                    content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Products not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return ResponseEntity.status((HttpStatus.OK)).build();
    }
}
