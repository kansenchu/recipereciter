package com.example.recipereciter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicRecipeController implements RecipeController {

    @Override
    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }
}
