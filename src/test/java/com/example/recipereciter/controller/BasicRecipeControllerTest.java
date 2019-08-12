package com.example.recipereciter.controller;

import com.example.recipereciter.controller.response.AllRecipesResponse;
import com.example.recipereciter.dto.Recipe;
import com.example.recipereciter.service.RecipeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.example.recipereciter.TestHelper.getOutputFile;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasicRecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @InjectMocks
    private BasicRecipeController basicRecipeController;

    @Test
    void getAllRecipes() throws Exception {
        // arrange
        // TODO: move to utils
        ObjectMapper objectMapper = new ObjectMapper();
        String allRecipesString = new String(Files.readAllBytes(
                Paths.get("src/test/resources/inputs/").resolve("allRecipes.json")));
        List<Recipe> recipeList = objectMapper.readValue(allRecipesString, new TypeReference<List<Recipe>>(){});
        when(recipeService.getAllRecipes()).thenReturn(recipeList);

        AllRecipesResponse expected = objectMapper.readValue(
                getOutputFile("AllRecipesResponse"), AllRecipesResponse.class);

        // act
        AllRecipesResponse actual = basicRecipeController.getAllRecipes();

        // assert
        assertEquals(expected, actual);
    }
}