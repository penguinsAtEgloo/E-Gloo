package com.project.egloo.recipe.domain;

import com.project.egloo.common.ColumnDescription;
import com.project.egloo.ingredient.domain.Ingredient;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Data
@DynamicInsert
public class Cooking {

    @Id
    @GeneratedValue
    @ColumnDescription("PK")
    private Long    id;

    @ManyToOne
    @ColumnDescription("FK 레시피 ID")
    private Recipe recipe;

    @ManyToOne
    @ColumnDescription("FK 재료 ID")
    private Ingredient ingredient;

    @ColumnDescription("수량")
    private int quantity;

    @Enumerated(EnumType.STRING)
    @ColumnDescription("단위")
    private Unit unit;

    @ColumnDescription("가격")
    private int price;

    @ColumnDescription("필수 여부")
    private boolean checkIngredients;
}
