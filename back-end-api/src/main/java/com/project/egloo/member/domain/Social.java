package com.project.egloo.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Social {
    LOCAL("LOCAL"),
    KAKAO("kakao"),
    FACEBOOK("facebook"),
    NAVER("naver"),
    GOOGLE("google"),;

    private final String value;

    public static Social of(String value){
        return Arrays.stream(Social.values())
                    .filter(it -> it.value == value)
                    .findFirst().orElse(null);
    }
}
