package com.project.egloo.recipe.dto;

import com.project.egloo.recipe.domain.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateRecipeDTO {

    private String categoryName;
    private String name;
    private String summary;
    private Difficulty difficulty;
    private String image;
    private int price;
}
