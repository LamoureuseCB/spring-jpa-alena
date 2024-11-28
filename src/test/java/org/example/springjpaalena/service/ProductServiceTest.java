package org.example.springjpaalena.service;

import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Product;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class ProductServiceTest {
    @Test
    void createProductWhenCategory_IdExists() {
        Category category = new Category();
        category.setId(1);
        category.setName("Продукты питания");
        Product product = new Product();
        product.setName("Хлеб");
        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);

        ProductService productService = new ProductService(categoryRepository, productRepository);
        int categoryId = category.getId();
        Mockito.when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(category));
        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(product);
        Product savedProduct = productService.create(product, categoryId);
        Assertions.assertEquals(product.getName(), savedProduct.getName());
        Assertions.assertEquals(category.getName(), savedProduct.getCategory().getName());
    }


}
