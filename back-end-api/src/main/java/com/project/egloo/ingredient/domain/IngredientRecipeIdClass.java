package com.project.egloo.ingredient.domain;

import com.project.egloo.recipe.domain.Recipe;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;


@Getter
@Data
public class IngredientRecipeIdClass implements Serializable {

    private Recipe recipe;
    private Ingredient ingredient;
}
