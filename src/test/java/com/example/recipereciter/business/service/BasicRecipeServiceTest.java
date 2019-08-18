package com.example.recipereciter.business.service;

import com.example.recipereciter.application.dto.Recipe;
import com.example.recipereciter.business.dao.RecipeDao;
import com.example.recipereciter.domain.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.util.List;

import static com.example.recipereciter.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasicRecipeServiceTest {

    @Mock
    Clock clock;

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    BasicRecipeService basicRecipeService;

    @Test
    void shouldFetchAllRecipes() {
        // arrange
        when(recipeRepository.findAll(Sort.by("id").ascending())).thenReturn(mockRecipeDaoList());
        List<Recipe> expected = mockRecipeList();

        // act
        List<Recipe> actual = basicRecipeService.getAllRecipes();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldFetchOneRecipe() {
        // arrange
        int idToGet = 1;
        when(recipeRepository.getOne(idToGet)).thenReturn(mockRecipeDao("firstRecipe"));
        Recipe expected = mockRecipe("firstRecipe");
        when(recipeRepository.existsById(idToGet)).thenReturn(true);

        // act
        Recipe actual = basicRecipeService.getRecipe(1);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldSendNewRecipe() {
        // arrange
        RecipeDao newRecipeDao = mockRecipeDao("newRecipe");

        Recipe newRecipe = new Recipe(newRecipeDao.getTitle(),
            newRecipeDao.getMakingTime(),
            newRecipeDao.getServes(),
            newRecipeDao.getIngredients(),
            newRecipeDao.getCost());

        RecipeDao recipeDaoToAdd = RecipeDao.builder()
            .title(newRecipeDao.getTitle())
            .makingTime(newRecipeDao.getMakingTime())
            .serves(newRecipeDao.getServes())
            .ingredients(newRecipeDao.getIngredients())
            .cost(newRecipeDao.getCost())
            .build();

        Recipe expected = mockRecipe("newRecipe");

        when(recipeRepository.save(recipeDaoToAdd)).thenReturn(newRecipeDao);

        // act
        Recipe actual = basicRecipeService.addRecipe(newRecipe);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldEditRecipe() {
        // arrange
        int idToEdit = 1;
        RecipeDao newRecipeDao = mockRecipeDao("newRecipe");
        RecipeDao oldRecipeDao = mockRecipeDao("firstRecipe");
        when(clock.instant()).thenReturn(Instant.now());
        when(recipeRepository.existsById(idToEdit)).thenReturn(true);

        Recipe editedRecipe = new Recipe(newRecipeDao.getTitle(),
            newRecipeDao.getMakingTime(),
            newRecipeDao.getServes(),
            newRecipeDao.getIngredients(),
            newRecipeDao.getCost());

        RecipeDao targetDao = RecipeDao.builder()
            .id(idToEdit)
            .title(newRecipeDao.getTitle())
            .makingTime(newRecipeDao.getMakingTime())
            .serves(newRecipeDao.getServes())
            .ingredients(newRecipeDao.getIngredients())
            .cost(newRecipeDao.getCost())
            .createdAt(oldRecipeDao.getCreatedAt())
            .updatedAt(Timestamp.from(Instant.now(clock)))
            .build();

        when(recipeRepository.getOne(idToEdit)).thenReturn(oldRecipeDao);
        when(recipeRepository.save(targetDao)).thenReturn(targetDao);
        Recipe expected = new Recipe(idToEdit,
            newRecipeDao.getTitle(),
            newRecipeDao.getMakingTime(),
            newRecipeDao.getServes(),
            newRecipeDao.getIngredients(),
            newRecipeDao.getCost());

        // act
        Recipe actual = basicRecipeService.editRecipe(idToEdit, editedRecipe);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteRecipe() {
        // arrange
        int idToDelete = 1;
        when(recipeRepository.existsById(idToDelete)).thenReturn(true);
        when(recipeRepository.getOne(idToDelete)).thenReturn(mockRecipeDao("firstRecipe"));
        Recipe expected = mockRecipe("firstRecipe");

        // act
        Recipe actual = basicRecipeService.deleteRecipe(idToDelete);

        // assert
        assertEquals(expected, actual);
        verify(recipeRepository).delete(mockRecipeDao("firstRecipe"));
    }
}