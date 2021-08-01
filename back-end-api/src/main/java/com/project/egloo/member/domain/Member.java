package com.project.egloo.member.domain;

import com.project.egloo.common.ColumnDescription;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Data
@DynamicInsert
public class Member {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @ColumnDescription("PK")
    private UUID id;
    
    @Column(unique = true)
    @NotNull@NotBlank
    @ColumnDescription("유저 아이디")
    private String userId;
    
    @ColumnDescription("유저 이름")
    private String name;

    @NotNull@NotBlank
    @Length(min = 8, max = 20)
    @Pattern(regexp = "^((?=.*[a-z0-9])(?=.*[A-Z]).{8,20})|((?=.*[a-z0-9])(?=.*[^a-zA-Z0-9가-힣]).{8,20})$")
    @ColumnDescription("비밀번호")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'LOCAL'")
    @ColumnDescription("유저 회원가입 경로")
    private Social social;

    @NotNull@NotBlank
    @ColumnDescription("유저 휴대폰 번호")
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    @ColumnDescription("성별")
    private Gender gender;

    @ColumnDescription("이메일")
    private String email;

    @ColumnDescription("주소")
    private String address;

    @ColumnDescription("유저 Role")
    private MemberRole role;

    public String roleName(){
        return role.name();
    }

}
