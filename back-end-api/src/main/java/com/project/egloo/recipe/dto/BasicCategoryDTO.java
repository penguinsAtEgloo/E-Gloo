package com.project.egloo.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class BasicCategoryDTO {

    private Long id;
    private String name;
    private OffsetDateTime createdAt;
}
