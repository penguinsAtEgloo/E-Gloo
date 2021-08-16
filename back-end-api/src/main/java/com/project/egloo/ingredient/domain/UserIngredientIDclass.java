package com.project.egloo.ingredient.domain;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Data
@Getter
public class UserIngredientIDclass implements Serializable {
    private UUID userId;
    private Long ingredientId;
}
