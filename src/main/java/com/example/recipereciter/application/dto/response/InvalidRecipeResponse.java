package com.example.recipereciter.application.dto.response;

import lombok.Value;

@Value
public class InvalidRecipeResponse {
  Message message;
  String required;
}
