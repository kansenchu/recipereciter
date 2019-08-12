package com.example.recipereciter.controller;

import com.example.recipereciter.dto.Recipe;
import com.example.recipereciter.service.RecipeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.example.recipereciter.TestHelper.getOutputFile;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BasicRecipeController.class)
class BasicRecipeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @InjectMocks
    BasicRecipeController basicRecipeController;

    @Test
    void shouldReturnAllRecipes() throws Exception {
        //setup mock
        ObjectMapper objectMapper = new ObjectMapper();
        String allRecipesString = new String(Files.readAllBytes(
                Paths.get("src/test/resources/inputs/").resolve("allRecipes.json")));
        List<Recipe> recipeList = objectMapper.readValue(allRecipesString, new TypeReference<List<Recipe>>(){});
        when(recipeService.getAllRecipes()).thenReturn(recipeList);

        String expected = getOutputFile("AllRecipesResponse");
        mockMvc.perform(get("/recipes")).andExpect(status().isOk())
                .andExpect(content().json(expected));
    }
}
