package com.example.recipereciter.service;

import com.example.recipereciter.application.dto.Recipe;
import com.example.recipereciter.service.exception.NoRecipeFoundException;

import java.util.List;

/**
 * レシピのサービスの契約を示すインタフェース。
 */
public interface RecipeService {
    /**
     * 全レシピ取得。
     *
     * @return 全レシピのリスト
     */
    List<Recipe> getAllRecipes();

    /**
     * 指定したIDのレシピを返す。
     *
     * @param id 欲しいレシピ
     * @return 指定したレシピ
     * @throws NoRecipeFoundException レシピ見つかれない時
     */
    Recipe getRecipe(int id) throws NoRecipeFoundException;

    Recipe addRecipe(Recipe newRecipe);

    Recipe editRecipe(int id, Recipe newRecipe);

    Recipe deleteRecipe(int id);
}
