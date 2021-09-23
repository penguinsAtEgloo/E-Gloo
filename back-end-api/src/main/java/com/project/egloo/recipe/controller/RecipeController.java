package com.project.egloo.recipe.controller;

import com.project.egloo.recipe.dto.CreateRecipeDTO;
import com.project.egloo.recipe.dto.DeleteResultDTO;
import com.project.egloo.recipe.dto.PatchRecipeDTO;
import com.project.egloo.recipe.dto.RecipeDTO;
import com.project.egloo.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/api/v1/recipes")
    public ResponseEntity<String> recipeByIngredients(
        @RequestParam List<String> ingredients
    ) {
        String msg = recipeService.getRecipeByIngredients(ingredients).toString();
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/api/v1/recipes")
    public ResponseEntity<RecipeDTO> createRecipe(
        @RequestBody CreateRecipeDTO recipeInfo
    ) {
        RecipeDTO recipe = recipeService.createRecipe(recipeInfo);

        return ResponseEntity.ok(recipe);
    }

    @PatchMapping("/api/v1/recipes/{recipeId}")
    public ResponseEntity<RecipeDTO> updateRecipe(
        @RequestBody PatchRecipeDTO patchRecipe,
        @PathVariable Long recipeId
    ) {
        RecipeDTO updatedRecipe = recipeService.updateRecipe(recipeId, patchRecipe);

        return ResponseEntity.ok(updatedRecipe);
    }

    @DeleteMapping("/api/v1/recipes/{recipeId}")
    public ResponseEntity<DeleteResultDTO> deleteRecipe(
        @PathVariable Long recipeId
    ) {
        recipeService.deleteRecipe(recipeId);

        return ResponseEntity.ok(new DeleteResultDTO(true));
    }
}