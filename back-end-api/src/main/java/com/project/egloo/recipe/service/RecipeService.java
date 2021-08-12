package com.project.egloo.recipe.service;

import com.project.egloo.ingredient.domain.Ingredient;
import com.project.egloo.ingredient.repository.IngredientRepository;
import com.project.egloo.recipe.domain.Cooking;
import com.project.egloo.recipe.repository.CookingRepository;
import com.project.egloo.recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final CookingRepository cookingRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, CookingRepository cookingRepository, IngredientRepository ingredientRepository){
        this.recipeRepository = recipeRepository;
        this.cookingRepository = cookingRepository;
        this.ingredientRepository = ingredientRepository;
    }


    public Object getRecipeByIngredients(ArrayList<String> ingredientNameList) {
        ArrayList intersection = new ArrayList();

        List<Ingredient> ingredientList = ingredientRepository.findByNameIn(ingredientNameList);
        List cooks = ingredientList.stream().map(obj -> cookingRepository.findByIngredient(obj)).collect(Collectors.toList());

        for(int i=0; i < cooks.size(); i++) {
            List<Cooking> cooksList = (List<Cooking>) cooks.get(i);
            intersection.add(cooksList.stream().map(obj -> obj.getRecipe().getName()).collect(Collectors.toList()));
        }
        return intersection(intersection);
    }


    public HashSet intersection(ArrayList inputArrays)
    {
        HashSet intersectionSet = new HashSet((Collection) inputArrays.get(0));
        for (int i = 1; i < inputArrays.size(); i++)
        {
            HashSet innerset = new HashSet((Collection) inputArrays.get(i));
            intersectionSet.retainAll(innerset);
        }
        return intersectionSet;
    }
}
