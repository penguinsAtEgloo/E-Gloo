package com.project.egloo.ingredient.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
public class IngredientIDClass implements Serializable {
    @Id
    private Long    id;
    @Id
    private String name;
}
