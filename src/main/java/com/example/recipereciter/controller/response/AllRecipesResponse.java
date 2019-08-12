package com.example.recipereciter.controller.response;

import java.util.List;

import com.example.recipereciter.dto.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 全レシピ返す用POJOクラス.
 */

@Data
@AllArgsConstructor
public class AllRecipesResponse {
    List<Recipe> recipes;
}
