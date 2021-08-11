package com.project.egloo.recipe.repository;

import com.project.egloo.ingredient.domain.Ingredient;
import com.project.egloo.recipe.domain.Cooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CookingRepository extends JpaRepository<Cooking, Long> {
    List<Cooking> findByIngredient(Ingredient ingredient);
}
