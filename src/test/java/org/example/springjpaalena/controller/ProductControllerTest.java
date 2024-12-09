package org.example.springjpaalena.controller;

import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Product;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.repository.ProductRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    @SneakyThrows
    void findProductById_WhenIdCorrect() {
        int productId = 1;
        mockMvc.perform(get("/products/" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.name", Matchers.equalTo("Intel Core I9 9900")))
                .andExpect(jsonPath("$.price", Matchers.equalTo(249990.0)))
                .andExpect(jsonPath("$.category.name", Matchers.equalTo("Процессоры")));
    }

    @Test
    @SneakyThrows
    void findAllProducts(){
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(6)));
    }
    @BeforeEach
    void setUpProducts(){
        Category category = new Category();
        category.setName("Кухонные приборы");
        categoryRepository.save(category);

        Product product1 = new Product();
        product1.setCategory(category);
        product1.setName("Блендер");
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setCategory(category);
        product2.setName("Чайник");
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setCategory(category);
        product3.setName("Кофеварка");
        productRepository.save(product3);


    }

    @Test
    @SneakyThrows
    void findAllByZeroPageAnd2Size(){

//        Запрос с указанием номера страницы как 0 и размера страницы как 2.
//2. Запрос без указания номера страницы и размера (использование значений по умолчанию).
//3. Запрос с отрицательным номером страницы или отрицательным размером страницы (ожидается статус 400).
        mockMvc.perform(get("/products/page/size")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }
    @Test
    @SneakyThrows
    void findAllWithoutPageAndSize(){
        mockMvc.perform(get("/products/page/size")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$",Matchers.hasSize(10)));
    }

    @Test
    @SneakyThrows
    void findAllByNegativePageAndSize(){
        mockMvc.perform(get("/products/page/size")
                        .param("page", "-1")
                        .param("size", "-2"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$",Matchers.hasSize(0)));
    }

}

