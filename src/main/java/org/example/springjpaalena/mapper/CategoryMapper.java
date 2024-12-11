package org.example.springjpaalena.mapper;

import lombok.RequiredArgsConstructor;
import org.example.springjpaalena.dto.category.CategoryCreateDto;
import org.example.springjpaalena.dto.category.CategoryFullDto;
import org.example.springjpaalena.dto.category.CategoryShortDto;
import org.example.springjpaalena.dto.category.CategoryWithOptionsResponseDto;
import org.example.springjpaalena.dto.option.OptionResponseDto;
import org.example.springjpaalena.dto.product.ProductDto;
import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Option;
import org.example.springjpaalena.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final ProductMapper productMapper;
    private final OptionMapper optionMapper;

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
    public CategoryCreateDto toCreateDto(Category category){
        return CategoryCreateDto.builder().name(category.getName()).options(category.getOptionList()).build();
    }

    public CategoryWithOptionsResponseDto toOptionsResponseDto(Category category){
        List<Option> options= category.getOptionList();
        List<OptionResponseDto> optionResponseDtos = optionMapper.toOptionResponseDtos(options);
        return  CategoryWithOptionsResponseDto.builder().id(category.getId()).name(category.getName()).options(optionResponseDtos).build();
    }

}




