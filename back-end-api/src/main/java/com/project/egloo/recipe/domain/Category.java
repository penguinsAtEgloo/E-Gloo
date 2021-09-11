package com.project.egloo.recipe.domain;

import com.project.egloo.common.ColumnDescription;
import com.project.egloo.recipe.dto.BasicCategoryDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    @ColumnDescription("PK")
    private Long id;

    @ColumnDescription("카테고리 이름")
    private String name;

    @OneToMany(mappedBy = "category")
    @ColumnDescription("레시피 (읽기전용, 양방향 연관관계 설정)")
    private List<Recipe> recipes = new ArrayList<>();

    @ColumnDescription("생성일자")
    private OffsetDateTime createdAt;

    public Category(String name) {
        this.name = name;
        this.createdAt = OffsetDateTime.now();
    }

    public BasicCategoryDTO toBasicDTO() {
        return new BasicCategoryDTO(
            this.id,
            this.name,
            this.createdAt
        );
    }
}
