package com.project.egloo.ingredient.domain;

import com.project.egloo.common.ColumnDescription;
import com.project.egloo.recipe.domain.Cooking;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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

    @ColumnDescription("재료명")
    private String name;

    @ColumnDescription("재료 이미지")
    private String ingredientImage;

    @OneToMany(mappedBy = "ingredient")
    @ColumnDescription("요리 (읽기전용, 양방향 연관관계 설정)")
    private List<Cooking> cookings = new ArrayList<>();
}