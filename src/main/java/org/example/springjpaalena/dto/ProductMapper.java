package org.example.springjpaalena.dto;

import org.example.springjpaalena.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product){
        return ProductDto.builder().id(product.getId())
                .name(product.getName()).price(product.getPrice()).build();
    }
    public List<ProductDto> toDto(List<Product> products){
        return products.stream()
                .map(this::toDto)
                .toList();
    }
}
