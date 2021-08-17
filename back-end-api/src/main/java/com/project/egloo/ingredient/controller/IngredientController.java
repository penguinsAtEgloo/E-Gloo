package com.project.egloo.ingredient.controller;

import com.project.egloo.common.ResponseEntityObject;
import com.project.egloo.common.exceptions.ErrorCode;
import com.project.egloo.ingredient.domain.Ingredient;
import com.project.egloo.ingredient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/getIngredient")
    public ResponseEntityObject selectUserIngredient(String userId){
        List<Ingredient> ingredients = ingredientService.userIngredient(UUID.fromString(userId));
        return new ResponseEntityObject(ErrorCode.SUCCESS.getCode(), ingredients.toString(),"");
    }
}
