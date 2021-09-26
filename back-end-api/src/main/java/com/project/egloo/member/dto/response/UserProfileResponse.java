package com.project.egloo.member.dto.response;

import com.project.egloo.member.domain.Gender;
import com.project.egloo.member.domain.MemberRole;
import com.project.egloo.member.domain.Social;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileResponse {

    private String name;
    private Social social;
    private String phoneNo;
    private Gender gender;
    private String email;
    private String address;
    private MemberRole role;
}
