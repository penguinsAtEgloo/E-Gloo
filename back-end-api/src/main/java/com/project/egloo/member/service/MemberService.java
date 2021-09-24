package com.project.egloo.member.service;

import com.project.egloo.common.exceptions.AuthException;
import com.project.egloo.common.exceptions.ErrorCode;
import com.project.egloo.member.domain.Member;
import com.project.egloo.member.domain.MemberRole;
import com.project.egloo.member.dto.request.SignUpRequest;
import com.project.egloo.member.dto.response.MemberDTO;
import com.project.egloo.member.mapper.MemberMapper;
import com.project.egloo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void throwIfUserInfoNotUnique(String userId, String email) {
        if (memberRepository.findByUserId(userId).isPresent()) {
            throw new AuthException(ErrorCode.DUPLICATED_ID);
        }

        if (memberRepository.findByEmail(email).isPresent()) {
            throw new AuthException(ErrorCode.DUPLICATED_EMAIL);
        }
    }

    @Transactional
    public MemberDTO memberSignUP(SignUpRequest signUpRequest) {
        Member member = Member.builder()
            .email(signUpRequest.getEmail())
            .password(passwordEncoder.encode(signUpRequest.getPassword()))
            .role(MemberRole.USER)
            .social(signUpRequest.getSocial())
            .address(signUpRequest.getAddress())
            .build();

        return MemberMapper.INSTANCE.toDTO(memberRepository.save(member));
    }
}
