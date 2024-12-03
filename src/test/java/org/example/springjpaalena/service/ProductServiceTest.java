package org.example.springjpaalena.service;

import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Product;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class ProductServiceTest {
    private Category category;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductService productService;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setName("Продукты питания");
    }
//
//    @Test
//    void createProductWhenCategory_IdExists() {
//
//        Product product = new Product();
//        product.setName("Хлеб");
//
//        Mockito.when(categoryRepository.findById(categoryId))
//                .thenReturn(Optional.of(category));
//        Mockito.when(productRepository.save(Mockito.any(Product.class)))
//                .thenReturn(product);
//        Product savedProduct = productService.create(product, categoryId);
//        Assertions.assertEquals(product.getName(), savedProduct.getName());
//        Assertions.assertEquals(category.getName(), savedProduct.getCategory().getName());
//    }


}
