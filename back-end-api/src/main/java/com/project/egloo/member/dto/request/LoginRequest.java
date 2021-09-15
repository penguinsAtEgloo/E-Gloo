package com.project.egloo.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull @Email
    private String userEmail;

    @NotNull
    @Pattern(regexp="^((?=.*[a-z0-9])(?=.*[A-Z]).{8,20})|((?=.*[a-z0-9])(?=.*[^a-zA-Z0-9가-힣]).{8,20})$")
    private String password;
}
