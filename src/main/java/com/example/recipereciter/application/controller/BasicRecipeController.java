package com.example.recipereciter.application.controller;

import com.example.recipereciter.application.dto.response.*;
import com.example.recipereciter.application.dto.Recipe;
import com.example.recipereciter.application.exception.InvalidRecipeException;
import com.example.recipereciter.application.exception.NoRecipeFoundException;
import com.example.recipereciter.business.service.RecipeService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    @GetMapping("/{id}")
    @JsonView(RecipeResponse.Views.WithoutRecipeId.class)
    public RecipeResponse getRecipe(@PathVariable int id) {
        return new RecipeResponse(Message.READ, recipeService.getRecipe(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping
    @JsonView(RecipeResponse.Views.WithRecipeId.class)
    public RecipeResponse addRecipe(@RequestBody Recipe newRecipe) {
        ArrayList<String> missingFields = new ArrayList<>();
        if (newRecipe.getTitle() == null) {
            missingFields.add("title");
        }
        if (newRecipe.getMakingTime() == null) {
            missingFields.add("making_time");
        }
        if (newRecipe.getServes() == null) {
            missingFields.add("serves");
        }
        if (newRecipe.getIngredients() == null) {
            missingFields.add("ingredients");
        }
        if (newRecipe.getCost() == null) {
            missingFields.add("cost");
        }
        if (!missingFields.isEmpty()) {
            throw new InvalidRecipeException(missingFields);
        }

        return new RecipeResponse(Message.CREATED, recipeService.addRecipe(newRecipe));
    }

    /**
     * レシピを変更するメソッド。
     * 指定していないフィルドは古いものを使うままにします。
     *
     * @param id        変えたいレシピのid
     * @param newRecipe 変えたいもの詳細
     * @return 変更したレシピ詳細
     */
    @Override
    @PatchMapping("/{id}")
    @JsonView(RecipeResponse.Views.WithoutRecipeId.class)
    public RecipeResponse editRecipe(@PathVariable int id, @RequestBody Recipe newRecipe) {
        return new RecipeResponse(Message.EDITED, recipeService.editRecipe(id, newRecipe));
    }

    /**
     * レシピを削除するメソッド。
     *
     * @param id 削除するレシピID
     * @return 削除成功レスポンス
     */
    @Override
    @DeleteMapping("/{id}")
    public MessageResponse deleteRecipe(@PathVariable int id) {
        Recipe deletedRecipe = recipeService.deleteRecipe(id);
        if (deletedRecipe.getId() != id) throw new NoRecipeFoundException();
        return new MessageResponse(Message.DELETED);
    }

    @ExceptionHandler(NoRecipeFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse noRecipeFoundHandler() {
        return new MessageResponse(Message.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRecipeException.class)
    @ResponseStatus(HttpStatus.OK)
    public InvalidRecipeResponse invalidRecipeHandler(InvalidRecipeException e){
        return new InvalidRecipeResponse(e);
    }
}
