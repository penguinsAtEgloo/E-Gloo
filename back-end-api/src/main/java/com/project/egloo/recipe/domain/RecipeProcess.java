package com.project.egloo.recipe.domain;

import com.project.egloo.common.ColumnDescription;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_process_id")
    @ColumnDescription("PK")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @ColumnDescription("FK 레시피 아이디")
    private Recipe recipe;

    @ColumnDescription("요리 설명 순서")
    private int priority;

    @ColumnDescription("요리 설명")
    private String description;

    @ColumnDescription("이미지 url")
    private String image;

    @ColumnDescription("과정 팁")
    private String tip;

    @ColumnDescription("생성일자")
    private OffsetDateTime createdAt;
}
