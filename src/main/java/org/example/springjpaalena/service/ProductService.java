package org.example.springjpaalena.service;

import lombok.RequiredArgsConstructor;
import org.example.springjpaalena.dto.product.ProductCreateDto;
import org.example.springjpaalena.dto.product.ProductWithValuesResponseDto;
import org.example.springjpaalena.dto.value.ValueCreateDto;
import org.example.springjpaalena.dto.value.ValueResponseDto;
import org.example.springjpaalena.error.CategoryNotFoundException;
import org.example.springjpaalena.error.OptionNotFound;
import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Option;
import org.example.springjpaalena.model.Product;
import org.example.springjpaalena.model.Value;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.repository.OptionRepository;
import org.example.springjpaalena.repository.ProductRepository;
import org.example.springjpaalena.repository.ValueRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;



@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;
    private final ValueRepository valueRepository;

    public Product create(Product product, int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Категория с ID " + categoryId + " не найдена"));
        product.setCategory(category);
        return productRepository.save(product);
    }

    public List<Product> findProductByValueName(String valueName) {
        return productRepository.findAllByValueListAndName(valueName);
    }

    public ProductWithValuesResponseDto create(int categoryId, ProductCreateDto productCreateDto) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Категория не найдена"));

        Product product = new Product();
        product.setName(productCreateDto.getName());
        product.setPrice(productCreateDto.getPrice());
        product.setCategory(category);
        product = productRepository.save(product);

        List<ValueResponseDto> responseValues = new ArrayList<>();

        for (ValueCreateDto valueCreateDto : productCreateDto.getValues()) {
            Option option = optionRepository.findById(valueCreateDto.getOptionId()).orElseThrow(() -> new OptionNotFound("Характеристика по ID " + valueCreateDto.getOptionId() + " не найдена"));

            Value value = new Value();
            value.setProduct(product);
            value.setOption(option);
            value.setName(valueCreateDto.getName());
            valueRepository.save(value);
            ValueResponseDto valueResponseDto = new ValueResponseDto(value.getId(), value.getName(), option.getName());
            responseValues.add(valueResponseDto);
        }
     return new ProductWithValuesResponseDto(product.getId(), product.getName(), product.getPrice(),category.getName(),responseValues);
    }
}
