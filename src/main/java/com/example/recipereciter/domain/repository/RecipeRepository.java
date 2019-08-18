package com.example.recipereciter.domain.repository;

import com.example.recipereciter.business.dao.RecipeDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeDao, Integer> {

}
