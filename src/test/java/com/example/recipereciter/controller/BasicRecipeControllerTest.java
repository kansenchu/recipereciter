package com.example.recipereciter.controller;

import com.example.recipereciter.controller.response.AllRecipesResponse;
import com.example.recipereciter.controller.response.RecipeResponse;
import com.example.recipereciter.dto.Recipe;
import com.example.recipereciter.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.recipereciter.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasicRecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @InjectMocks
    private BasicRecipeController basicRecipeController;

    @Test
    void getAllRecipes() {
        // arrange
        when(recipeService.getAllRecipes()).thenReturn(mockRecipeList());
        AllRecipesResponse expected = mockAllRecipesResponse();

        // act
        AllRecipesResponse actual = basicRecipeController.getAllRecipes();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void getOneRecipe() {
        // arrange
        when(recipeService.getRecipe(any(Integer.class))).thenReturn(mockFirstRecipe());
        RecipeResponse expected = new RecipeResponse(RecipeResponse.Message.READ, mockFirstRecipe());

        // act
        RecipeResponse actual = basicRecipeController.getRecipe(1);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void addRecipe() {
        // arrange
        Recipe newRecipe = mockNewRecipe();
        when(recipeService.addRecipe(any(Recipe.class))).thenReturn(newRecipe);
        RecipeResponse expected = mockAddNewRecipeResponse();

        // act
        RecipeResponse actual = basicRecipeController.addRecipe(newRecipe);

        // assert
        assertEquals(expected, actual);
    }
}