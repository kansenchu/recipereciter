package com.example.recipereciter.controller.response;

import com.example.recipereciter.dto.Recipe;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Collections;
import java.util.List;

/**
 * 一個のレシピを返すためのレスポンスクラス。
 */
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@JsonView(RecipeResponse.Views.WithoutRecipeId.class)
public class RecipeResponse {

    /**
     * レシピレスポンス上で、レシピとして表示可能の方式を表すクラス。
     * 詳細は{@link Recipe}で参照。
     * @see JsonView
     */
    public static class Views {
        public interface WithoutRecipeId extends Recipe.Views.WithoutId {}
        public interface WithRecipeId extends Recipe.Views.WithId, RecipeResponse.Views.WithoutRecipeId {}
    }

    /** 作業の結果を表すメッセージ。*/
    Message message;

    /** リスト化された、返すレシピ。 */
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    List<Recipe> recipe;

    /**
     * 簡単に作成できるためのコンストラクタ。
     * @param msg 返したいメッセージ
     * @param newRecipe 返したいレシピ
     */
    public RecipeResponse(Message msg, Recipe newRecipe) {
        this(msg, Collections.singletonList(newRecipe));
    }
}
