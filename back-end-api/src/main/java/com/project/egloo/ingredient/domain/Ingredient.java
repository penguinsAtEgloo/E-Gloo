package com.project.egloo.ingredient.domain;

import com.project.egloo.common.ColumnDescription;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@IdClass(IngredientIDClass.class)
@Data
@DynamicInsert
public class Ingredient {

    @Id @GeneratedValue
    @ColumnDescription("PK")
    private Long    id;

    @Id @ColumnDescription("PK")
    private String name;

    @ColumnDescription("재료 이미지")
    private String ingredientImage;

    @ColumnDescription("가격")
    private int price;
}