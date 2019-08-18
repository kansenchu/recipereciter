package com.example.recipereciter.business.service;

import com.example.recipereciter.application.dto.Recipe;
import com.example.recipereciter.business.dao.RecipeDao;
import com.example.recipereciter.domain.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.recipereciter.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasicRecipeServiceTest {

    @Mock
    RecipeRepository recipeRepo;

    @InjectMocks
    BasicRecipeService basicRecipeService;

    @Test
    void shouldGetAllRecipes() throws Exception {
        // arrange
        when(recipeRepo.getAllRecipes()).thenReturn(mockRecipeDaoList());
        List<Recipe> expected = mockRecipeList();

        // act
        List<Recipe> actual = basicRecipeService.getAllRecipes();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetOneRecipe() {
        // arrange
        when(recipeRepo.getRecipe(1)).thenReturn(mockRecipeDao("firstRecipe"));
        Recipe expected = mockRecipe("firstRecipe");

        // act
        Recipe actual = basicRecipeService.getRecipe(1);

        // assert
        assertEquals(expected, actual);
    }
}