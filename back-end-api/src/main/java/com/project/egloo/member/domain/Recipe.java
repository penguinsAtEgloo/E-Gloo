package com.project.egloo.member.domain;

import com.project.egloo.common.ColumnDescription;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Data
@DynamicInsert
public class Recipe {

    @Id @GeneratedValue
    @ColumnDescription("PK")
    private Long    id;

    @ManyToOne
    @ColumnDescription("FK 카테고리 아이디")
    private Category category;

    @ColumnDescription("간략정보")
    private String  summary;

    @ColumnDescription("레시피 이름")
    private String  name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'MIDDLE'")
    @ColumnDescription("레시피 난이도")
    private Difficulty  difficulty;

    @ColumnDescription("별점")
    private Double  ratings;

    @ColumnDescription("생성일자")
    private Date    createTime;

    @ColumnDescription("수정일자")
    private Date    updateTime;

    @ColumnDescription("레시피 이미지")
    private String  recipeImage;

    @ColumnDescription("재료 총 가격")
    private int     price;

    @ColumnDescription("찜 수")
    private int     countLike;

    @ColumnDescription("리뷰 수")
    private int     countReview;
}
