package com.example.recipereciter.service;

import com.example.recipereciter.dto.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();

    Recipe getRecipe(int id);

    Recipe addRecipe(Recipe newRecipe);

    Recipe editRecipe(int id, Recipe newRecipe);

    Recipe deleteRecipe(int id);
}
