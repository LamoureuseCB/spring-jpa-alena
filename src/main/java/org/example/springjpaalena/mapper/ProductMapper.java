package org.example.springjpaalena.mapper;

import lombok.RequiredArgsConstructor;
import org.example.springjpaalena.dto.product.ProductCreateDto;
import org.example.springjpaalena.dto.product.ProductDto;
import org.example.springjpaalena.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ValueMapper valueMapper;

    public ProductDto toDto(Product product){
        return ProductDto.builder().id(product.getId())
                .name(product.getName()).price(product.getPrice()).build();
    }
    public List<ProductDto> toDto(List<Product> products){
        return products.stream()
                .map(this::toDto)
                .toList();
    }

    public ProductCreateDto toProductCreateDto(Product product){
    return ProductCreateDto.builder()
            .name(product.getName())
            .price(product.getPrice())
            .values(valueMapper.toValueCreateDtos(product.getValueList())).build();
    }
    public List<ProductCreateDto> toProductCreateDtos(List<Product> products){
        return products.stream().map(this::toProductCreateDto).toList();
    }
}

