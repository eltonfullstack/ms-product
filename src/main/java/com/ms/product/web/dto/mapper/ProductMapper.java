package com.ms.product.web.dto.mapper;

import com.ms.product.entity.Product;
import com.ms.product.web.dto.ProductCreateDto;
import com.ms.product.web.dto.ProductResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ProductMapper {

    public static Product toProduct(ProductCreateDto productCreateDto) {
        return new ModelMapper().map(productCreateDto, Product.class);
    }

    public static ProductResponseDto toProductDto(Product product) {
        return new ModelMapper().map(product, ProductResponseDto.class);
    }

    public static List<ProductResponseDto> toListDto(List<Product> products) {
        return products.stream().map(ProductMapper::toProductDto).toList();
    }
}
