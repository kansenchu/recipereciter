package com.example.recipereciter.controller;

import com.example.recipereciter.controller.response.AllRecipesResponse;
import com.example.recipereciter.controller.response.RecipeResponse;
import com.example.recipereciter.dto.Recipe;
import com.example.recipereciter.service.RecipeService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 最低限のRESTのレシピのコントローラーを表現する。
 */
@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class BasicRecipeController implements RecipeController {

    /**
     * 連携しているレシピサービス。
     * コストラクターインジェクションを採用する。
     */
    private final RecipeService recipeService;

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping
    public AllRecipesResponse getAllRecipes() {
        return new AllRecipesResponse(recipeService.getAllRecipes());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(value = "/{id}")
    @JsonView(RecipeResponse.Views.WithoutRecipeId.class)
    public RecipeResponse getRecipe(@PathVariable int id) {
        return new RecipeResponse(RecipeResponse.Message.READ, recipeService.getRecipe(id));
    }

    /**
     * @{inheritDoc}
     */
    @Override
    @PostMapping
    @JsonView(RecipeResponse.Views.WithRecipeId.class)
    public RecipeResponse addRecipe(@RequestBody Recipe newRecipe) {
        return new RecipeResponse(RecipeResponse.Message.CREATED, recipeService.addRecipe(newRecipe));
    }
}
