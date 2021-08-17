package com.project.egloo.recipe.controller;

import com.project.egloo.common.ResponseEntityObject;
import com.project.egloo.common.exceptions.ErrorCode;
import com.project.egloo.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;


    @GetMapping("/getRecipeByIngredients")
    public ResponseEntityObject recipeByIngredients(@RequestParam List<String> ingredients){
        return new ResponseEntityObject(ErrorCode.SUCCESS.getCode(), recipeService.getRecipeByIngredients(ingredients).toString(),"");
    }
}