package com.example.recipereciter.business.dao;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

/**
 * DBとやりとりする時使うレシピクラス。
 * ポイントとして、cost (値段)がintの基準でしています。
 */
@Builder
@Value
public class RecipeDao {

  public static class RecipeDaoBuilder {
  }

  /** レシピの識別番号 */
  private int id;

  /** レシピの名前。 */
  private String title;

  /** レシピの作り時間。 */
  private String makingTime;

  /** レシピに対応する人数。 */
  private String serves;

  /** 材料リスト。Listではなく、String扱いとしています. */
  private String ingredients;

  /** レシピの予測値段。 */
  private String cost;

  /** レシピの作成時間。 */
  private Timestamp createdAt;

  /** レシピの作成時間。 */
  private Timestamp updatedAt;

}