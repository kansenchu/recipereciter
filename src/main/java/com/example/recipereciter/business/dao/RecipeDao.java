package com.example.recipereciter.business.dao;

import java.sql.Timestamp;

import lombok.*;

/**
 * DBとやりとりする時使うレシピクラス。
 * ポイントとして、cost (値段)がintの基準でしています。
 */
@Value
@Builder
public class RecipeDao {
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