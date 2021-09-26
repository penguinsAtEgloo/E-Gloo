package com.project.egloo.member.dto.request;

import com.project.egloo.member.domain.Gender;
import com.project.egloo.member.domain.Social;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String name;

    @NotBlank
    @Length(min = 8, max = 20)
    // 영문 소문자 숫자 + 영어 대문자 OR 영문 소문자 숫자 + 특수문자
    @Pattern(regexp = "^((?=.*[a-z0-9])(?=.*[A-Z]).{8,20})|((?=.*[a-z0-9])(?=.*[^a-zA-Z0-9가-힣]).{8,20})$")
    private String password;

    @NotNull
    private Social social;

    @NotBlank
    private String phoneNo;

    @NotNull
    private Gender gender;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String address;

}
