package com.project.egloo.ingredient.domain;

import com.project.egloo.common.ColumnDescription;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Data
@DynamicInsert
public class Ingredient {

    @Id @GeneratedValue
    @ColumnDescription("PK")
    private Long    id;

    @ColumnDescription("PK")
    private String name;

    @ColumnDescription("PK")
    private String ingredientImage;

    @ColumnDescription("PK")
    private int price;
}