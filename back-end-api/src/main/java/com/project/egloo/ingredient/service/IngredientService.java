package com.project.egloo.ingredient.service;

import com.project.egloo.ingredient.domain.Ingredient;
import com.project.egloo.ingredient.domain.UserIngredient;
import com.project.egloo.ingredient.repository.IngredientRepository;
import com.project.egloo.ingredient.repository.UserIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final UserIngredientRepository userIngredientRepository;
    private final IngredientRepository ingredientRepository;

    public List<Ingredient> userIngredient(UUID userId){
        List<Long> ingredientsIds = userIngredientRepository.findByUserId(userId).stream().map(obj -> obj.getIngredientId()).collect(Collectors.toList());
        List<Ingredient> ingredients = ingredientRepository.findAllById(ingredientsIds);
        return ingredients;
    }

}
