package com.project.egloo.member.service;

import com.project.egloo.member.domain.Member;
import com.project.egloo.member.domain.MemberRole;
import com.project.egloo.member.domain.Social;
import com.project.egloo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;

    @Override
    @SneakyThrows
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuth2Attribute oAuth2Attribute = OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        saveUser(registrationId, oAuth2Attribute);
        var memberAttribute = oAuth2Attribute.convertToMap();

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(MemberRole.USER.getRole())), memberAttribute, "email");
    }

    public void saveUser(String provider, final OAuth2Attribute attributes) {
        // 이미 등록된 유저는 아무 동작없이 함수 종료, 로그인으로 진행합니다
        memberRepository.findByUserId(attributes.getName()).orElseGet(() -> {
            Member member = new Member();
            member.setUserId(attributes.getName());
            member.setEmail(attributes.getEmail());
            member.setSocial(Social.of(provider));
            member.setRole(MemberRole.USER);
            memberRepository.save(member);
            return member;
        });
    }
}
