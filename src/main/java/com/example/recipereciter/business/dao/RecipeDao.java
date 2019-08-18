package com.example.recipereciter.business.dao;

import java.sql.Timestamp;

import lombok.*;

import javax.persistence.*;

/**
 * DBとやりとりする時使うレシピクラス。
 * ポイントとして、cost (値段)がintの基準でしています。
 */
@Entity
@Table(name = "recipes")
@Data
@Builder
public class RecipeDao {

  public static class RecipeDaoBuilder {
  }

  /** レシピの識別番号 */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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