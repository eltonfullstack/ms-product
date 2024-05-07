package com.ms.product.web.dto.mapper;

import com.ms.product.entity.Product;
import com.ms.product.web.dto.ProductCreateDto;
import com.ms.product.web.dto.ProductResponseDto;
import org.modelmapper.ModelMapper;

public class ProductMapper {

    public static Product toProduct(ProductCreateDto productCreateDto) {
        return new ModelMapper().map(productCreateDto, Product.class);
    }

    public static ProductResponseDto toProductDto(Product product) {
        return new ModelMapper().map(product, ProductResponseDto.class);
    }
}
