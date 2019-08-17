package com.example.recipereciter.application.exception;

import lombok.Getter;

import java.util.List;

/**
 * 入力されたレシピに必要な情報が欠けてるを示すエクセプションクラス。
 */
@Getter
public class InvalidRecipeException extends RuntimeException {
  /* 欠けているフィールドを示すオブジェクト */
  private List<String> missingFields;

  /**
   * 基本のコンストラクタ。
   * @param missingFieldsArray 欠けているフィールドのリスト。
   */
  public InvalidRecipeException(List<String> missingFieldsArray) {
    missingFields = missingFieldsArray;
  }

}
