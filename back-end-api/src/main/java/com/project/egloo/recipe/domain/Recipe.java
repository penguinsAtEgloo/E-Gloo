package com.project.egloo.recipe.domain;

import com.project.egloo.common.ColumnDescription;
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
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    @ColumnDescription("PK")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ColumnDescription("FK 카테고리 아이디")
    private Category category;

    @ColumnDescription("간략정보")
    private String summary;

    @ColumnDescription("레시피 이름")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'MIDDLE'")
    @ColumnDescription("레시피 난이도")
    private Difficulty difficulty;

    @ColumnDescription("별점")
    private Double ratings;

    @ColumnDescription("레시피 이미지")
    private String image;

    @ColumnDescription("재료 총 가격")
    private int price;

    @ColumnDescription("찜 수")
    private int countLike;

    @ColumnDescription("리뷰 수")
    private int countReview;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    @ColumnDescription("요리 (읽기 전용, 양방향 연관관계 설정)")
    private List<Cooking> cookings = new ArrayList<>();

    @OneToMany(mappedBy = "recipe")
    @ColumnDescription("요리 순서 (읽기 전용, 양방향 연관관계 설정)")
    private List<RecipeProcess> recipeProcesses = new ArrayList<>();

    @ColumnDescription("생성일자")
    private OffsetDateTime createdAt;

    @ColumnDescription("수정일자")
    private OffsetDateTime updatedAt;
}
