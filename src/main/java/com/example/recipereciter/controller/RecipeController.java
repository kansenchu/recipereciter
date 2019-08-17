package com.example.recipereciter.controller;

import com.example.recipereciter.controller.response.AllRecipesResponse;
import com.example.recipereciter.controller.response.MessageResponse;
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

    /**
     * 一個のレシピを取得する。
     *
     * @param id 取得したいレシピの識別番号
     * @return リクエストしたレシピ
     */
    RecipeResponse getRecipe(int id);

    /**
     * レシピを追加するメソッド。
     *
     * @param newRecipe 追加したいレシピの詳細
     * @return 追加したレシピのデータ。特に、識別番号の追加
     */
    RecipeResponse addRecipe(Recipe newRecipe);

    /**
     * レシピを変更するメソッド。
     * 指定していないフィルドは古いものを使うままにします。
     *
     * @param id 変えたいレシピのid
     * @param newRecipe 変えたいもの詳細
     * @return 変更したレシピ詳細
     */
    RecipeResponse editRecipe(int id, Recipe newRecipe);

    /**
     * レシピを削除するメソッド。
     *
     * @param id 削除するレシピID
     * @return 削除成功レスポンス
     */
    MessageResponse deleteRecipe(int id);
}
