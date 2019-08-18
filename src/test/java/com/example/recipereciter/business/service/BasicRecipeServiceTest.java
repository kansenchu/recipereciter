package com.example.recipereciter.business.service;

import com.example.recipereciter.application.dto.Recipe;
import com.example.recipereciter.business.dao.RecipeDao;
import com.example.recipereciter.domain.repository.RecipeJpaRepository;
import com.example.recipereciter.domain.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.example.recipereciter.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasicRecipeServiceTest {

    @Mock
    RecipeRepository recipeRepo;

    @Mock
    RecipeJpaRepository recipeJpaRepository;

    @InjectMocks
    BasicRecipeService basicRecipeService;

    @Test
    void shouldFetchAllRecipes() {
        // arrange
        when(recipeJpaRepository.findAll(Sort.by("id").ascending())).thenReturn(mockRecipeDaoList());
        List<Recipe> expected = mockRecipeList();

        // act
        List<Recipe> actual = basicRecipeService.getAllRecipes();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldFetchOneRecipe() {
        // arrange
        when(recipeJpaRepository.getOne(1)).thenReturn(mockRecipeDao("firstRecipe"));
        Recipe expected = mockRecipe("firstRecipe");

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

        when(recipeJpaRepository.save(recipeDaoToAdd)).thenReturn(newRecipeDao);

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

        Recipe editedRecipe = new Recipe(newRecipeDao.getTitle(),
            newRecipeDao.getMakingTime(),
            newRecipeDao.getServes(),
            newRecipeDao.getIngredients(),
            newRecipeDao.getCost());

        RecipeDao.RecipeDaoBuilder builder = RecipeDao.builder()
            .title(newRecipeDao.getTitle())
            .makingTime(newRecipeDao.getMakingTime())
            .serves(newRecipeDao.getServes())
            .ingredients(newRecipeDao.getIngredients())
            .cost(newRecipeDao.getCost());

        RecipeDao editInputDao = builder.build();
        RecipeDao editOutputDao = builder.id(idToEdit).build();

        when(recipeRepo.updateRecipe(idToEdit, editInputDao)).thenReturn(editOutputDao);
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
        when(recipeRepo.deleteRecipe(idToDelete)).thenReturn(mockRecipeDao("firstRecipe"));
        Recipe expected = mockRecipe("firstRecipe");

        // act
        Recipe actual = basicRecipeService.deleteRecipe(idToDelete);

        // assert
        assertEquals(expected, actual);
    }
}