package com.project.egloo.member.service;

import com.project.egloo.common.ResponseEntityObject;
import com.project.egloo.common.exceptions.AuthException;
import com.project.egloo.common.exceptions.ErrorCode;
import com.project.egloo.member.domain.Member;
import com.project.egloo.member.domain.MemberRole;
import com.project.egloo.member.domain.Social;
import com.project.egloo.member.dto.request.SignUpRequest;
import com.project.egloo.member.dto.response.SignUpResponse;
import com.project.egloo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRespository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponse memberSignUP(SignUpRequest signUpRequest, Errors errors) throws Exception {
        if (errors.hasErrors()) {
            throw new Exception();
        }
        memberRespository.findByUserId(signUpRequest.getUserId()).orElseThrow(()->new AuthException(ErrorCode.DUPLICATED_ID));
        memberRespository.findByEmail(signUpRequest.getEmail()).orElseThrow(()->new AuthException(ErrorCode.DUPLICATED_EMAIL));

        Member member = Member.builder()
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .role(MemberRole.ROLE_USER)
                .social(Social.LOCAL)
                .address(signUpRequest.getAddress())
                .build();


        member.setPassword(passwordEncoder.encode(member.getPassword()));

        memberRespository.save(member);
        return SignUpResponse.of(member.getId());
    }
}
