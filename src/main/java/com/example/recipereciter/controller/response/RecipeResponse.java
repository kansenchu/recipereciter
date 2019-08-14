package com.example.recipereciter.controller.response;

import com.example.recipereciter.dto.Recipe;
import com.example.recipereciter.dto.RecipeViews;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 一個のレシピを返すためのレスポンスクラス。
 */
@Value
@RequiredArgsConstructor
@JsonView(RecipeViews.WithoutId.class)
public class RecipeResponse {

    /**
     * 各種の返せるメッセージを表現するEnum。
     */
    public enum Message {
        READ("Recipe details by id");

        private String message;

        Message(String msg) {
            message = msg;
        }

        @JsonValue
        public String getMessage() {
            return message;
        }
    }

    /** 作業の結果を表すメッセージ。*/
    Message message;

    /** 返すレシピ */
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    List<Recipe> recipe;

    public RecipeResponse(Message msg, Recipe newRecipe) {
        this(msg, Collections.singletonList(newRecipe));
    }
}
