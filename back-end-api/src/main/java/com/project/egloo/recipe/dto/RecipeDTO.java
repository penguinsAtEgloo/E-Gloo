package com.project.egloo.recipe.dto;

import com.project.egloo.recipe.domain.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class RecipeDTO {

    private Long id;
    private BasicCategoryDTO category;
    private String name;
    private String summary;
    private Difficulty difficulty;
    private Double ratings;
    private String image;
    private int price;
    private int countLike;
    private int countReview;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
