package org.example.springjpaalena.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.example.springjpaalena.dto.category.CategoryShortDto;
import org.example.springjpaalena.dto.product.ProductCreateDto;
import org.example.springjpaalena.dto.value.ValueCreateDto;
import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Product;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.repository.ProductRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    @Autowired
    private ObjectMapper objectMapper;

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
    void findAllProducts() {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(6)));
    }
//
//    @BeforeEach
//    void setUpProducts() {
//        Category category = new Category();
//        category.setName("Кухонные приборы");
//        categoryRepository.save(category);
//
//        Product product1 = new Product();
//        product1.setCategory(category);
//        product1.setName("Блендер");
//        productRepository.save(product1);
//
//        Product product2 = new Product();
//        product2.setCategory(category);
//        product2.setName("Чайник");
//        productRepository.save(product2);
//
//        Product product3 = new Product();
//        product3.setCategory(category);
//        product3.setName("Кофеварка");
//        productRepository.save(product3);
//
//
//    }

    @Test
    @SneakyThrows
    void findAllByZeroPageAnd2Size() {
        mockMvc.perform(get("/products/page/size")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    @SneakyThrows
    void findAllWithoutPageAndSize() {
        mockMvc.perform(get("/products/page/size")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(10)));
    }

    @Test
    @SneakyThrows
    void findAllByNegativePageAndSize() {
        mockMvc.perform(get("/products/page/size")
                        .param("page", "-1")
                        .param("size", "-2"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    @SneakyThrows
    void createProductCategoryExists() {
        Category category = new Category();
        category.setName("testCategory");

        String categoryJson = objectMapper.writeValueAsString(category);
        String categoryResponseJson = mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CategoryShortDto categoryShortDto = objectMapper.readValue(categoryResponseJson, CategoryShortDto.class);
        int categoryId = categoryShortDto.getId();

        Product product = new Product();
        product.setName("Test product");

        mockMvc.perform(post("/products/" + categoryId)
                        .content(objectMapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Test product"));
    }

    @Test
    @SneakyThrows
    void createProductCategoryNotExists() {
        int categoryId = 10000;
        Product product = new Product();
        product.setName("Test product");

        mockMvc.perform(post("/products/" + categoryId)
                        .content(objectMapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Категория не найдена"));
    }

    @Test
    @SneakyThrows
    void createProductWithValuesAndOptions() {
        ProductCreateDto productCreateDto = new ProductCreateDto();
        productCreateDto.setName("Блендер");
        productCreateDto.setPrice(50000.0);
        productCreateDto.setValues(List.of(new ValueCreateDto(1, "1200Вт"), new ValueCreateDto(2, "300 оборотов")));

        String responseJson = mockMvc.perform(post("/products/3")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(productCreateDto)))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name", Matchers.equalTo("Блендер")))
                .andExpect(jsonPath("$.price", Matchers.equalTo(50000.0)))
                .andExpect(jsonPath("$.categoryName", Matchers.equalTo("Кухонные приборы")))
                .andExpect(jsonPath("$.values", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.values[0].name", Matchers.equalTo("1200Вт")))
                .andExpect(jsonPath("$.values[0].optionName", Matchers.equalTo("Мощность")))
                .andExpect(jsonPath("$.values[0].name", Matchers.equalTo("300 оборотов")))
                .andExpect(jsonPath("$.values[0].optionName", Matchers.equalTo("Количество оборотов")))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
    }
}

