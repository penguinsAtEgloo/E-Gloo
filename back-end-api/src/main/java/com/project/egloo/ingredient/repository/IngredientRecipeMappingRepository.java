package com.project.egloo.ingredient.repository;

import com.project.egloo.ingredient.domain.IngredientRecipeIdClass;
import com.project.egloo.ingredient.domain.IngredientRecipeMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRecipeMappingRepository extends JpaRepository<IngredientRecipeMapping, IngredientRecipeIdClass> {
    List<IngredientRecipeMapping> findByIngredient_id(Long ingredient_id);
}
