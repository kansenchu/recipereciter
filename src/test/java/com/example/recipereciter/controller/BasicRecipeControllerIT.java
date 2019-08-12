package com.example.recipereciter.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BasicRecipeController.class)
class BasicRecipeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BasicRecipeController basicRecipeController;

    @Test
    void shouldReturnAllRecipes() throws Exception {
        mockMvc.perform(get("/recipes")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }
}
