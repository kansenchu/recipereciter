package com.example.recipereciter.application.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.*;
import lombok.experimental.NonFinal;

/**
 * REST API でやりとりする時使うレシピクラス。
 * ポイントとして、値段がStringになります。
 */
@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonView(Recipe.Views.WithoutId.class)
@RequiredArgsConstructor
public class Recipe {

    /**
     * レシピとして表示可能の方式を表すクラス。
     * 実際に何が表示するかの判定は、まずクラス単位で定義した@JsonViewアノテーションが標準で、
     * クラス内のフィールドでオーバライドする。
     * @see JsonView
     */
    public static class Views {
        /** id だけ抜いているパターン */
        public interface WithoutId {}
        /** 全部のフィールドが入っているパターン */
        public interface WithId extends Views.WithoutId {}
    }

    /** レシピの識別する番号 */
    @JsonView(Recipe.Views.WithId.class)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @NonFinal
    int id;

    /** レシピの名前 */
    final String title;

    /** レシピの作り時間。実際JSONではmaking_timeになります*/
    final String makingTime;

    /** レシピに対応する人数 */
    final String serves;

    /** 材料リスト。Listではなく、String扱いとしています。 */
    final String ingredients;

    /** レシピの予測値段。intではなく, Stringです。 */
    final String cost;

}