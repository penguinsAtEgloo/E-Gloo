package com.project.egloo.recipe.service;

import com.project.egloo.ingredient.domain.Ingredient;
import com.project.egloo.ingredient.repository.IngredientRepository;
import com.project.egloo.recipe.domain.Cooking;
import com.project.egloo.recipe.repository.CookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeService {

    private final CookingRepository cookingRepository;
    private final IngredientRepository ingredientRepository;


    public HashSet getRecipeByIngredients(List<String> ingredientNames) {
        List<List<String>> intersection = new ArrayList();

        List<Ingredient> ingredients = ingredientRepository.findByNameIn(ingredientNames);
        List<List<Cooking>> cooks = ingredients.stream().map(obj -> cookingRepository.findByIngredient(obj)).collect(Collectors.toList());

        for(int i=0; i < cooks.size(); i++) {
            List<Cooking> cookings = cooks.get(i);
            intersection.add(cookings.stream().map(obj -> obj.getRecipe().getName()).collect(Collectors.toList()));
        }
        return intersection(intersection);
    }


    public HashSet intersection(List<List<String>> recipeNames)
    {
        HashSet intersectionSet = new HashSet(recipeNames.get(0));
        for (int i = 1; i < recipeNames.size(); i++)
        {
            HashSet innerset = new HashSet(recipeNames.get(i));
            intersectionSet.retainAll(innerset);
        }
        return intersectionSet;
    }
}
