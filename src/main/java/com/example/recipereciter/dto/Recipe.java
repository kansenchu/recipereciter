package com.example.recipereciter.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

/**
 * REST API でやりとりする時使うレシピクラス。
 * ポイントとして、値段がStringになります。
 */
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonView(RecipeViews.WithoutId.class)
public class Recipe {

    /** レシピの識別する番号 */
    @JsonView(RecipeViews.WithId.class)
    int id;

    /** レシピの名前 */
    String title;

    /** レシピの作り時間。実際JSONではmaking_timeになります*/
    String makingTime;

    /** レシピに対応する人数 */
    String serves;

    /** 材料リスト。Listではなく、String扱いとしています。 */
    String ingredients;

    /** レシピの予測値段。intではなく, Stringです。 */
    String cost;

}