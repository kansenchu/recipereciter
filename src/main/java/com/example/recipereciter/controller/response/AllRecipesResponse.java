package com.example.recipereciter.controller.response;

import java.util.List;

import com.example.recipereciter.dto.Recipe;
import lombok.*;

/**
 * 全レシピ返す用POJOクラス.
 */
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AllRecipesResponse {
    /** 全レシピを格納されているリスト。 */
    List<Recipe> recipes;
}
