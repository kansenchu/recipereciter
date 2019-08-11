package com.example.recipereciter.controller;

import org.springframework.stereotype.Controller;

@Controller
public class BasicRecipeController implements RecipeController {
    @Override
    public String helloWorld() {
        return "Hello World";
    }
}
