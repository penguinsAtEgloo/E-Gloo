package com.project.egloo.member.domain;

import com.project.egloo.common.ColumnDescription;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Data
@DynamicInsert
public class RecipeProcess {

    @Id @GeneratedValue
    @ColumnDescription("PK")
    private Long    id;
    
    @ManyToOne
    @ColumnDescription("FK 레시피 아이디")
    private Recipe recipe;

    @ColumnDescription("요리 설명 순서")
    private int ROrd;

    @ColumnDescription("요리 설명")
    private String Description;

    @ColumnDescription("이미지 url")
    private String ImgUrl;

    @ColumnDescription("과정 팁")
    private String Tip;
    
}
