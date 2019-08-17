package com.example.recipereciter.application.exception;

import java.util.List;

/**
 * 入力されたレシピに必要な情報が欠けてるを示すエクセプションクラス。
 */
public class InvalidRecipeException extends RuntimeException {
  /** 欠けているフィールドを示すオブジェクト */
  String missingFields;

  public InvalidRecipeException(List<String> missingFieldsArray) {

  }
}
