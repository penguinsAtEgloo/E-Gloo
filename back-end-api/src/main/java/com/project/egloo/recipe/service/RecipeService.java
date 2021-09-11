package com.project.egloo.recipe.service;

import com.project.egloo.ingredient.domain.Ingredient;
import com.project.egloo.ingredient.repository.IngredientRepository;
import com.project.egloo.recipe.domain.Category;
import com.project.egloo.recipe.domain.Cooking;
import com.project.egloo.recipe.domain.Recipe;
import com.project.egloo.recipe.dto.CreateRecipeDTO;
import com.project.egloo.recipe.dto.RecipeDTO;
import com.project.egloo.recipe.exception.CategoryNotFoundException;
import com.project.egloo.recipe.repository.CategoryRepository;
import com.project.egloo.recipe.repository.CookingRepository;
import com.project.egloo.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeService {

    private final CookingRepository cookingRepository;
    private final IngredientRepository ingredientRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public HashSet getRecipeByIngredients(List<String> ingredientNames) {
        List<List<String>> intersection = new ArrayList();

        List<Ingredient> ingredients = ingredientRepository.findByNameIn(ingredientNames);
        List<List<Cooking>> cooks = ingredients.stream().map(obj -> cookingRepository.findByIngredient(obj)).collect(Collectors.toList());

        for (int i = 0; i < cooks.size(); i++) {
            List<Cooking> cookings = cooks.get(i);
            intersection.add(cookings.stream().map(obj -> obj.getRecipe().getName()).collect(Collectors.toList()));
        }
        return intersection(intersection);
    }


    public HashSet intersection(List<List<String>> recipeNames) {
        HashSet intersectionSet = new HashSet(recipeNames.get(0));
        for (int i = 1; i < recipeNames.size(); i++) {
            HashSet innerset = new HashSet(recipeNames.get(i));
            intersectionSet.retainAll(innerset);
        }
        return intersectionSet;
    }

    public RecipeDTO createRecipe(CreateRecipeDTO recipeInfo) {
        Category category = categoryRepository.findByName(recipeInfo.getCategoryName())
            .orElseThrow(() -> new CategoryNotFoundException("No category where name: " + recipeInfo.getCategoryName()));

        Recipe recipe = new Recipe(
            category,
            recipeInfo.getName(),
            recipeInfo.getSummary(),
            recipeInfo.getDifficulty(),
            recipeInfo.getImage(),
            recipeInfo.getPrice()
        );

        return recipeRepository.save(recipe).toDTO();
    }
}
