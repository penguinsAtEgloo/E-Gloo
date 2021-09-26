package com.project.egloo.member.service;

import com.project.egloo.member.domain.Gender;
import com.project.egloo.member.domain.Member;
import com.project.egloo.member.domain.MemberRole;
import com.project.egloo.member.domain.Social;
import com.project.egloo.member.dto.request.SignUpRequest;
import com.project.egloo.member.dto.response.MemberDTO;
import com.project.egloo.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    @Test
    public void memberSignUpTest() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest(
            "googit",
            "이영훈",
            "securepass12",
            Social.LOCAL,
            "010-1234-1234",
            Gender.MALE,
            "email@gmail.com",
            "서울 강남구"
        );

        Member member = new Member(
            "googit",
            "이영훈",
            "encrypted-password-encrypted-password",
            Social.LOCAL,
            "010-1234-1234",
            Gender.MALE,
            "email@gmail.com",
            "서울 강남구",
            MemberRole.USER
        );
        UUID uuid = UUID.randomUUID();
        member.setId(uuid);

        // when
        when(passwordEncoder.encode("securepass12")).thenReturn("encrypted-password-encrypted-password");
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        MemberDTO savedMember = memberService.memberSignUP(signUpRequest);

        // then
        assertThat(savedMember.getId()).isEqualTo(uuid);
        assertThat(savedMember.getUserId()).isEqualTo("googit");
        assertThat(savedMember.getName()).isEqualTo("이영훈");
        assertThat(savedMember.getSocial()).isEqualTo(Social.LOCAL);
        assertThat(savedMember.getPhoneNo()).isEqualTo("010-1234-1234");
        assertThat(savedMember.getEmail()).isEqualTo("email@gmail.com");
        assertThat(savedMember.getAddress()).isEqualTo("서울 강남구");
        assertThat(savedMember.getRole()).isEqualTo(MemberRole.USER);
    }
}