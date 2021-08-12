package com.project.egloo.recipe.controller;

import com.project.egloo.common.ResponseEntityObject;
import com.project.egloo.common.exceptions.ErrorCode;
import com.project.egloo.recipe.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }


    @GetMapping("/getRecipeByIngredients")
    public Object recipeByIngredients(@RequestParam ArrayList<String> ingredient){
        return new ResponseEntityObject(ErrorCode.SUCCESS.getCode(), recipeService.getRecipeByIngredients(ingredient).toString(),"");
    }
}