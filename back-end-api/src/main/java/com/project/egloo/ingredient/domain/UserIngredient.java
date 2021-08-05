package com.project.egloo.ingredient.domain;

import com.project.egloo.recipe.domain.Unit;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Data
public class UserIngredient {

    @Id
    private UUID userId;


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumns( value = {
            @JoinColumn(name = "ingredient_id", updatable = false, insertable = false),
            @JoinColumn(name = "ingredient_name", updatable = false, insertable = false)
    })
    private Ingredient ingredient;

    private String name;

    private int quantitiy;

    @Enumerated(EnumType.STRING)
    private Unit unit;

}
