package com.example.recipereciter.controller;

import com.example.recipereciter.controller.response.AllRecipesResponse;
import com.example.recipereciter.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicRecipeController implements RecipeController {

    private RecipeService recipeService;

    BasicRecipeController(RecipeService service) {
        recipeService = service;
    }

    @Override
    @GetMapping("/recipes")
    public AllRecipesResponse getAllRecipes() {
        return new AllRecipesResponse(recipeService.getAllRecipes());
    }
}
