package org.example.springjpaalena.repository;

import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setName("Продукты питания");
        categoryRepository.save(category);
    }

    @Test
    void findAllByPriceBetween() {
        Product product1 = new Product();
        product1.setPrice(10000);
        product1.setName(" name1");
        product1.setCategory(category);
        Product product2 = new Product();
        product2.setPrice(20000);
        product2.setName(" name2");
        product2.setCategory(category);

        Product product3 = new Product();
        product3.setPrice(15000);
        product3.setName(" name3");
        product3.setCategory(category);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        List<Product> productsBetweenPrice = productRepository.findAllByPriceBetween(10000, 15000);

        Assertions.assertEquals(2, productsBetweenPrice.size());

    }

    @Test
    void findAllByNameContainingIgnoreCaseOrderByPriceDescTest() {
        Product product = new Product();
        product.setCategory(category);
        product.setName("Smartphone");
        product.setPrice(100000);
        productRepository.save(product);

        String nameIgnorecase = "smart";
        List<Product> result = productRepository.findAllByNameContainingIgnoreCaseOrderByPriceDesc(nameIgnorecase);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(product.getName(), result.get(0).getName());
    }
}