package com.project.egloo.member.dto.response;

import com.project.egloo.member.domain.MemberRole;
import com.project.egloo.member.domain.Social;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private UUID id;
    private String userId;
    private String name;
    private Social social;
    private String phoneNo;
    private String email;
    private String address;
    private MemberRole role;
}
