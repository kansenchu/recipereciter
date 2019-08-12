package com.example.recipereciter.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * REST API でやりとりする時使うレシピクラス
 * ポイントとして、値段がStringになります。
 * @author pikachoo
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Recipe {

    private int id;

    private String title;			/** レシピの名前 */

    private String makingTime;		/** レシピの作り時間。実際JSONではmaking_timeになります*/

    private String serves;			/** レシピに対応する人数 */

    private String ingredients;		/** 材料リスト。Listではなく、String扱いとしています。 */

    private String cost;			/** レシピの予測値段。intではなく, Stringです。 */

}