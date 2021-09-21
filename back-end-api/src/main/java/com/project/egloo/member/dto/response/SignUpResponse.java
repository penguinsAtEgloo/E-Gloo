package com.project.egloo.member.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class SignUpResponse {

    private UUID uuid;

    public static SignUpResponse of(UUID id) {
        return new SignUpResponse(id);
    }
}
