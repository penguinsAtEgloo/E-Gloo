package com.project.egloo.ingredient.domain;

import com.project.egloo.common.ColumnDescription;
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
    @ColumnDescription("PK")
    private UUID userId;

    @ManyToOne
    @ColumnDescription("재료 ID")
    private Ingredient ingredient;

    @ColumnDescription("수량")
    private int quantitiy;

    @Enumerated(EnumType.STRING)
    @ColumnDescription("단위")
    private Unit unit;

}
