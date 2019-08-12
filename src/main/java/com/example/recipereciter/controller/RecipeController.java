package com.example.recipereciter.controller;

import com.example.recipereciter.controller.response.AllRecipesResponse;

public interface RecipeController {
    String helloWorld();

    AllRecipesResponse getAllRecipes();
}
