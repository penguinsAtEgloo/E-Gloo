package com.project.egloo.ingredient.domain;

import com.project.egloo.common.ColumnDescription;
import com.project.egloo.recipe.domain.Unit;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@IdClass(UserIngredientIDclass.class)
@Getter
@Setter
@Data
public class UserIngredient {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @ColumnDescription("PK")
    private UUID userId;

    @Id
    @ColumnDescription("재료 ID")
    private Long ingredientId;

    @ColumnDescription("수량")
    private int quantitiy;

    @Enumerated(EnumType.STRING)
    @ColumnDescription("단위")
    private Unit unit;

}
