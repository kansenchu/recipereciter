package com.example.recipereciter.application.dto.response;

import com.example.recipereciter.application.exception.InvalidRecipeException;
import lombok.Value;

@Value
public class InvalidRecipeResponse {
  String message = "Recipe creation failed!";
  String required;

  public InvalidRecipeResponse(InvalidRecipeException e) {
    required = String.join(", ", e.getMissingFields());
  }
}
