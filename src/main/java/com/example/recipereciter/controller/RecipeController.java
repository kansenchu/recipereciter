package com.example.recipereciter.controller;

import com.example.recipereciter.controller.response.AllRecipesResponse;
import com.example.recipereciter.controller.response.RecipeResponse;
import com.example.recipereciter.dto.Recipe;

/**
 * レシピのコントローラーの契約を示すインタフェース。
 */
public interface RecipeController {
    /**
     * 全レシピを取得する。
     *
     * @return 全部のレシピが含めているレスポンス
     */
    AllRecipesResponse getAllRecipes();

    RecipeResponse getRecipe(int i);

    RecipeResponse addRecipe(Recipe newRecipe);
}
