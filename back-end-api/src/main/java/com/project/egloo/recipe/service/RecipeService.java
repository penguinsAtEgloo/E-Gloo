package com.project.egloo.recipe.service;

import com.project.egloo.common.ResponseEntityObject;
import com.project.egloo.common.exceptions.ErrorCode;
import com.project.egloo.ingredient.domain.Ingredient;
import com.project.egloo.ingredient.repository.IngredientRepository;
import com.project.egloo.recipe.domain.Cooking;
import com.project.egloo.recipe.repository.CookingRepository;
import com.project.egloo.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CookingRepository cookingRepository;
    @Autowired
    private IngredientRepository ingredientRepository;


    public Object getRecipeByIngredients(ArrayList ingredient_list) {
        ArrayList intersection = new ArrayList();

        for(int i=0; i < ingredient_list.size(); i++){
            Optional<Ingredient> ingredient = ingredientRepository.findByName(ingredient_list.get(i).toString());
            List<Cooking> cooking_list = cookingRepository.findByIngredient(ingredient.get());
            List recipeId = new ArrayList();
            System.out.println(cooking_list.toString());

            for(int j=0; j < cooking_list.size(); j++){
                Cooking map = cooking_list.get(j);
                recipeId.add(map.getRecipe().getName());
            }

            intersection.add(recipeId);
        }
        return new ResponseEntityObject(ErrorCode.SUCCESS.getCode(), intersection(intersection).toString(),"");

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
