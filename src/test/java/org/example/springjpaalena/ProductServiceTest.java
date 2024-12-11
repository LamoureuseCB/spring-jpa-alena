package org.example.springjpaalena;

import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Product;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.repository.OptionRepository;
import org.example.springjpaalena.repository.ProductRepository;
import org.example.springjpaalena.repository.ValueRepository;
import org.example.springjpaalena.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.function.Executable;

import java.util.NoSuchElementException;
import java.util.Optional;

public class ProductServiceTest {
    @Test
    void create_ProductWhenCategoryIdExists() {
        Category category = new Category();
        category.setId(1);
        category.setName("Продукты питания");
        Product product = new Product();
        product.setName("Хлеб");
        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        OptionRepository optionRepository = Mockito.mock(OptionRepository.class);
        ValueRepository valueRepository = Mockito.mock(ValueRepository.class);
        ProductService productService = new ProductService(categoryRepository, productRepository,optionRepository,valueRepository);
        int categoryId = category.getId();
        Mockito.when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(category));
        productService.create(product, categoryId);
        Assertions.assertEquals("Хлеб", product.getName());


    }

    @Test
    void create_ProductWithoutCategory_thenThrowException() {
        Product product = new Product();
        product.setName("Хлеб");
        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        OptionRepository optionRepository = Mockito.mock(OptionRepository.class);
        ValueRepository valueRepository = Mockito.mock(ValueRepository.class);
        ProductService productService = new ProductService(categoryRepository, productRepository,optionRepository,valueRepository);
        Mockito.when(categoryRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.empty());
        Executable executable = () -> productService.create(product,0);
        Assertions.assertThrows(NoSuchElementException.class, executable);


    }
}
