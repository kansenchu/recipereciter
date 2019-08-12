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
}
