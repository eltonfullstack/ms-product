package com.ms.product.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductCreateDto {

    private String name;
    private String image;
    private String description;
    private Double price;

}