package com.project.egloo.ingredient.domain;

import com.project.egloo.common.ColumnDescription;
import com.project.egloo.recipe.domain.Category;
import com.project.egloo.recipe.domain.Recipe;
import com.project.egloo.recipe.domain.Unit;
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
@IdClass(IngredientRecipeIdClass.class)
public class IngredientRecipeMapping {


    @ManyToOne
    @Id
    @ColumnDescription("FK 레시피 ID")
    private Recipe recipe;

    @ManyToOne
    @Id
    @ColumnDescription("FK 재료 ID")
    private Ingredient ingredient;

    @ManyToOne
    @ColumnDescription("FK 카테고리 ID")
    private Category category;

    @ColumnDescription("수량")
    private int quantity;

    @ColumnDescription("단위")
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @ColumnDescription("필수 여부")
    private boolean checkIngredient;
}
