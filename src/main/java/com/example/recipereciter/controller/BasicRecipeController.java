package com.example.recipereciter.controller;

import com.example.recipereciter.controller.response.AllRecipesResponse;
import com.example.recipereciter.dto.Recipe;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class BasicRecipeController implements RecipeController {

    @Override
    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }

    @Override
    @GetMapping("/recipes")
    public AllRecipesResponse getAllRecipes() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String allRecipesString = new String(Files.readAllBytes(
                    Paths.get("src/test/resources/inputs/").resolve("allRecipes.json")));
            List<Recipe> recipeList = objectMapper.readValue(allRecipesString, new TypeReference<List<Recipe>>(){});
            return new AllRecipesResponse(recipeList);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
