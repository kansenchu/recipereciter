package com.example.recipereciter.application.controller;

import com.example.recipereciter.application.dto.response.AllRecipesResponse;
import com.example.recipereciter.application.dto.response.Message;
import com.example.recipereciter.application.dto.response.MessageResponse;
import com.example.recipereciter.application.dto.response.RecipeResponse;
import com.example.recipereciter.application.dto.Recipe;
import com.example.recipereciter.application.exception.InvalidRecipeException;
import com.example.recipereciter.business.service.RecipeService;
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
        RecipeResponse expected = new RecipeResponse(Message.READ, mockFirstRecipe());

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

    @Test
    void addInvalidRecipe() {
        // arrange
        Recipe newRecipe = new Recipe(0, null, null, null, null, null);

        // act + assert
        assertThrows(InvalidRecipeException.class, () -> basicRecipeController.addRecipe(newRecipe));
    }

    @Test
    void editRecipe() {
        // arrange
        Recipe newRecipe = mockEditedRecipe();
        when(recipeService.editRecipe(1, newRecipe)).thenReturn(mockEditedRecipe());
        RecipeResponse expected = mockEditFirstRecipeResponse();

        // act
        RecipeResponse actual = basicRecipeController.editRecipe(1, newRecipe);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void removeRecipe() {
        // arrange
        Recipe deletedRecipe = mockRecipe("firstRecipe");
        when(recipeService.deleteRecipe(1)).thenReturn(deletedRecipe);
        MessageResponse expected = mockMessageResponse("deletedResponse");

        //act
        MessageResponse actual = basicRecipeController.deleteRecipe(1);

        // assert
        assertEquals(expected, actual);
    }
}