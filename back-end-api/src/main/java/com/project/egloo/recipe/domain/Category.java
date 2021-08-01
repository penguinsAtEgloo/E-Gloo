package com.project.egloo.recipe.domain;


import com.project.egloo.common.ColumnDescription;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Data
public class Category {

    @Id@GeneratedValue
    @ColumnDescription("PK")
    private Long    id;

    @ColumnDescription("카테고리 이름")
    private String  name;
}
