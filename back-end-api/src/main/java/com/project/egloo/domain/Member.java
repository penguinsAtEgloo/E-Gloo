package com.project.egloo.domain;

import com.project.egloo.common.ColumnDescription;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @ColumnDescription("PK")
    private UUID id;

    @ColumnDescription("유저 이름")
    private String name;

    @ColumnDescription("비밀번호")
    private String password;

    @Enumerated(EnumType.STRING)
    @ColumnDescription("유저 회원가입 경로")
    private Social social;

    @ColumnDescription("유저 휴대폰 번호")
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    @ColumnDescription("성별")
    private Gender gender;

    @ColumnDescription("이메일")
    private String email;

    @ColumnDescription("주소")
    private String address;
}
