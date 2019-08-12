package com.example.recipereciter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class BasicRecipeController implements RecipeController {

    @Override
    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }

    @Override
    @GetMapping("/recipes")
    public String getAllRecipes() {
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/resources/outputs/").resolve("AllRecipesResponse.json")));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
