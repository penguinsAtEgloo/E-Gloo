package com.project.egloo.recipe.dto;

import com.project.egloo.recipe.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchRecipeDTO {

    private String categoryName;
    private String name;
    private String summary;
    private Difficulty difficulty;
    private String image;
    private Integer price;
}
