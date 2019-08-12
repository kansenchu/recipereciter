package com.example.recipereciter.controller;

import com.example.recipereciter.dto.Recipe;
import com.example.recipereciter.service.RecipeService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasicRecipeControllerTest {

    private BasicRecipeController basicRecipeController = new BasicRecipeController(() -> null);

    @Test
    void helloWorldCheck() {
        assertEquals("Hello World", basicRecipeController.helloWorld());
    }
}