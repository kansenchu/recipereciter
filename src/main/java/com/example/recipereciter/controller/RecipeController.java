package com.example.recipereciter.controller;

import com.example.recipereciter.controller.response.AllRecipesResponse;

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
}
