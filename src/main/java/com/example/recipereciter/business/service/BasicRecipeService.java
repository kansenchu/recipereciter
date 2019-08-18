package com.example.recipereciter.business.service;

import com.example.recipereciter.application.dto.Recipe;
import com.example.recipereciter.business.dao.RecipeDao;
import com.example.recipereciter.domain.repository.RecipeJpaRepository;
import com.example.recipereciter.domain.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 最低限のレシピのサービスを表現する。
 */
@Service
@RequiredArgsConstructor
public class BasicRecipeService implements RecipeService {

    private RecipeJpaRepository recipeJpaRepository;
    private RecipeRepository recipeRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> list = new ArrayList<>();
        for (RecipeDao recipeDao : recipeJpaRepository.findAll(Sort.by("id").ascending())) {
            Recipe recipe = daoToRecipe(recipeDao);
            list.add(recipe);
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Recipe getRecipe(int id) {
        return daoToRecipe(recipeJpaRepository.getOne(id));
    }

    @Override
    public Recipe addRecipe(Recipe newRecipe) {
        return daoToRecipe(recipeJpaRepository.save(recipeToDao(newRecipe)));
    }

    @Override
    public Recipe editRecipe(int id, Recipe newRecipe) {
        return daoToRecipe(recipeRepository.updateRecipe(id, recipeToDao(newRecipe)));
    }

    @Override
    public Recipe deleteRecipe(int id) {
        return daoToRecipe(recipeRepository.deleteRecipe(id));
    }

    private Recipe daoToRecipe(RecipeDao recipeDao) {
        return new Recipe(recipeDao.getId(),
                        recipeDao.getTitle(),
                        recipeDao.getMakingTime(),
                        recipeDao.getServes(),
                        recipeDao.getIngredients(),
                        recipeDao.getCost()
        );
    }

    private RecipeDao recipeToDao(Recipe recipe) {
        RecipeDao.RecipeDaoBuilder builder = RecipeDao.builder();

        if (recipe.getId() != 0) builder.id(recipe.getId());
        builder.title(recipe.getTitle());
        builder.makingTime(recipe.getMakingTime());
        builder.serves(recipe.getServes());
        builder.ingredients(recipe.getIngredients());
        builder.cost(recipe.getCost());

        return builder.build();
    }
}
