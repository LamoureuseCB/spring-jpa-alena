package org.example.springjpaalena.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.example.springjpaalena.dto.category.CategoryCreateDto;
import org.example.springjpaalena.dto.category.CategoryShortDto;
import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Option;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.repository.OptionRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void findAll_shouldReturnListOfCategory() {
        mockMvc.perform(get("/categories/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(4)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("Процессоры")));
    }

    @Test
    @SneakyThrows
    void createCategory_shouldReturnCreatedCategory() {
        Category category = new Category();
        category.setName("Категория из MockMvc");
        String json = objectMapper.writeValueAsString(category);
        String responseJson = mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id", Matchers.not(0)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("Категория из MockMvc")))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

       CategoryShortDto savedCategory = objectMapper.readValue(responseJson, CategoryShortDto.class);
       int categoryId = savedCategory.getId();

    }

    @Test
    @SneakyThrows
    void createCategory_shouldReturnCategoryAndOptions() {
       CategoryCreateDto categoryCreateDto = new CategoryCreateDto();
        categoryCreateDto.setName("Кухонные приборы");
        Option option1 = new Option();
        option1.setName("Мощность");
        optionRepository.save(option1);

        Option option2= new Option();
        option2.setName("Количество оборотов");
        optionRepository.save(option2);
        categoryCreateDto.setOptions(List.of(option1,option2));

        String responseJson = mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryCreateDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name", Matchers.equalTo("Кухонные приборы")))
                .andExpect(jsonPath("$.options", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.options[0].name", Matchers.equalTo("Мощность")))
                .andExpect(jsonPath("$.options[1].name", Matchers.equalTo("Количество оборотов")))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

    }
}