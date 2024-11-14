package org.example.springjpaalena.dto;

import lombok.RequiredArgsConstructor;
import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final ProductMapper productMapper;

    public CategoryShortDto toDto(Category category) {
        return CategoryShortDto.builder().id(category.getId()).name(category.getName()).build();
    }

    public CategoryFullDto toDtoFull(Category category) {
        List<Product>products = category.getProducts();
        List<ProductDto> productDtos = productMapper.toDto(products);
        return CategoryFullDto.builder().id(category.getId())
                .name(category.getName()).products(productDtos).build();
    }

    public List<CategoryShortDto> toDto(List<Category> categoryList){
        return categoryList.stream().map(this::toDto).toList();
    }
}




