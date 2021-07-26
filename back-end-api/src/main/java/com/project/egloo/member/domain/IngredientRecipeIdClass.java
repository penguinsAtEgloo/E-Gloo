package com.project.egloo.member.domain;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;


@Getter
@Data
public class IngredientRecipeIdClass implements Serializable {

    private Recipe recipe;
    private Ingredient ingredient;
}
