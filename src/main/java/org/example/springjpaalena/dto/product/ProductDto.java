package org.example.springjpaalena.dto.product;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private int id;
    private String name;
    private double price;
}
