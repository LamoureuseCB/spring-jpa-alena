package org.example.springjpaalena;

import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

//    @Test
//    @SneakyThrows
//    void findById_shouldReturnCategory_whenCorrectIdGiven() {
//        int categoryId = 1;
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/categories/" + categoryId);
//        ResultActions result = mockMvc.perform(requestBuilder);
//        result.andExpect(MockMvcResultMatchers.jsonPath("$").isMap())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Процессоры"));
//    }

    @Test
    @SneakyThrows
    void findAll_shouldReturnListOfCategory() {
        mockMvc.perform(get("/categories/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(4)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("Процессоры")));
    }
}
