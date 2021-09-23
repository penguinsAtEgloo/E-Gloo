package com.project.egloo.recipe.controller;

import com.project.egloo.common.ResponseEntityObject;
import com.project.egloo.common.exceptions.ErrorCode;
import com.project.egloo.recipe.dto.CreateRecipeDTO;
import com.project.egloo.recipe.dto.DeleteResultDTO;
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

    @GetMapping("/getRecipeByIngredients")
    public ResponseEntityObject recipeByIngredients(@RequestParam List<String> ingredients) {
        return new ResponseEntityObject(ErrorCode.SUCCESS.getCode(), recipeService.getRecipeByIngredients(ingredients).toString(), "");
    }

    @PostMapping("/api/v1/recipes")
    public ResponseEntity<RecipeDTO> createRecipe(
        @RequestBody CreateRecipeDTO recipeInfo
    ) {
        RecipeDTO recipe = recipeService.createRecipe(recipeInfo);

        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/api/v1/recipes/{recipeId}")
    public ResponseEntity<DeleteResultDTO> deleteRecipe(
        @PathVariable Long recipeId
    ) {
        recipeService.deleteRecipe(recipeId);

        return ResponseEntity.ok(new DeleteResultDTO(true));
    }
}