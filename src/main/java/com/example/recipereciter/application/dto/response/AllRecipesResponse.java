package com.example.recipereciter.application.dto.response;

import java.util.List;

import com.example.recipereciter.application.dto.Recipe;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

/**
 * 全レシピ返す用POJOクラス.
 */
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonView(Recipe.Views.WithId.class)
public class AllRecipesResponse {
    /** 全レシピを格納されているリスト。 */
    List<Recipe> recipes;
}
