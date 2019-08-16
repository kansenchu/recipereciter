package com.example.recipereciter;

import com.example.recipereciter.controller.response.AllRecipesResponse;
import com.example.recipereciter.controller.response.RecipeResponse;
import com.example.recipereciter.dto.Recipe;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHelper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Map<String, Object> mockObjectCache = new HashMap<>(30);

    public static String mapToString(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static String getFile(String filename) throws IOException {
        return filename.contains("Response") ? getOutputFile(filename) : getInputFile(filename);
    }

    public static List<Recipe> mockRecipeList() {
        return retrieveFromCache("allRecipes",
                objectMapper.getTypeFactory().constructCollectionType(List.class, Recipe.class));

    }

    public static AllRecipesResponse mockAllRecipesResponse() {
        return retrieveFromCache("allRecipesResponse",
                objectMapper.getTypeFactory().constructType(AllRecipesResponse.class));
    }

    public static RecipeResponse mockGetFirstRecipeResponse() {
        return retrieveFromCache("getFirstRecipeResponse",
                objectMapper.getTypeFactory().constructType(RecipeResponse.class));
    }

    public static RecipeResponse mockAddNewRecipeResponse() {
        return retrieveFromCache("addNewRecipeResponse",
                objectMapper.getTypeFactory().constructType(RecipeResponse.class));
    }

    public static Recipe mockFirstRecipe() {
        return retrieveFromCache("firstRecipe", objectMapper.getTypeFactory().constructType(Recipe.class));
    }

    public static Recipe mockNewRecipe() {
        return retrieveFromCache("newRecipe", objectMapper.getTypeFactory().constructType(Recipe.class));
    }

    private static String getOutputFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/outputs/").resolve(filename + ".json")));
    }

    private static String getInputFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/inputs/").resolve(filename + ".json")));
    }

    private static <T> T retrieveFromCache(String filename, JavaType type) {
        mockObjectCache.computeIfAbsent(filename, (key) -> {
            try {
                return objectMapper.readValue(getFile(filename), type);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });

        //noinspection unchecked
        return (T) mockObjectCache.get(filename);
    }
}