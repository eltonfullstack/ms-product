package com.ms.product.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductCreateDto {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Image cannot be blank")
    @Size(max = 255, message = "Image must be less than 255 characters")
    private String image;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    private Double price;

}
