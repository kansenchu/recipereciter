package com.example.recipereciter.service;

import com.example.recipereciter.dto.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicRecipeService implements RecipeService {
    @Override
    public List<Recipe> getAllRecipes() {
        return null;
    }

    @Override
    public Recipe getRecipe(int id) {
        return null;
    }

    @Override
    public Recipe addRecipe(Recipe newRecipe) {
        return null;
    }

    @Override
    public Recipe editRecipe(int id, Recipe newRecipe) {
        return null;
    }

    @Override
    public Recipe deleteRecipe(int id) {
        return null;
    }
}
