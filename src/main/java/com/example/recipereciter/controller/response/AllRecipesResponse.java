package com.example.recipereciter.controller.response;

import java.util.List;

import com.example.recipereciter.dto.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全レシピ返す用POJOクラス.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllRecipesResponse {
    List<Recipe> recipes;
}
