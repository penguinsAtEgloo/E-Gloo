package com.project.egloo.ingredient.domain;

import com.project.egloo.recipe.domain.Unit;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Data
public class RecipeOption implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumns( value = {
            @JoinColumn(name = "ingredient_id", updatable = false, insertable = false),
            @JoinColumn(name = "ingredient_name", updatable = false, insertable = false)
    })
    private Ingredient ingredient;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private int price;

    private boolean checkIngredients;
}
