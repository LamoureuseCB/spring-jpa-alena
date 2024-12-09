package org.example.springjpaalena.service;

import lombok.RequiredArgsConstructor;
import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Product;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public Product create(Product product, int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new NoSuchElementException("Категория с ID " + categoryId + " не найдена"));
        product.setCategory(category);
        return productRepository.save(product);
    }
    public List<Product> findProductByValueName(String valueName){
       return productRepository.findAllByValueListAndName(valueName);
    }


}
