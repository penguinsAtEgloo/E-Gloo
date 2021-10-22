package com.project.egloo.member.service;

import com.project.egloo.common.exceptions.AuthException;
import com.project.egloo.common.exceptions.ErrorCode;
import com.project.egloo.member.domain.Member;
import com.project.egloo.member.domain.MemberRole;
import com.project.egloo.member.dto.request.SignUpRequest;
import com.project.egloo.member.dto.response.MemberDTO;
import com.project.egloo.member.dto.response.ProfileDTO;
import com.project.egloo.member.mapper.MemberMapper;
import com.project.egloo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;


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

    public ProfileDTO memberInfo(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new AuthException(ErrorCode.ENTITY_NOT_FOUND));
        MemberDTO memberDTO = MemberMapper.INSTANCE.toDTO(member);
        return new ProfileDTO(memberDTO);
    }


    public String tokenDecoder(String token) throws ParseException {
        Base64.Decoder decoder = Base64.getDecoder();
        final String[] splitJwt = token.split("\\.");
        final String payload = new String(decoder.decode(splitJwt[1].getBytes()));

        JSONParser jsonParser = new JSONParser();
        //3. To Object
        Object obj = jsonParser.parse(payload);
        //4. To JsonObject
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj.get("sub").toString();
    }

    public Member memberUpdate(Member member) {
        if(!memberRepository.existsByEmail(member.getEmail())) {
            throw new AuthException(ErrorCode.ENTITY_NOT_FOUND);
        }
        Member members = memberRepository.findByEmail(member.getEmail()).get();
        members.builder()
            .userId(member.getUserId())
            .name(member.getName())
            .password(passwordEncoder.encode(member.getPassword()))
            .phoneNo(member.getPhoneNo())
            .email(member.getEmail())
            .address(member.getAddress())
            .build();
        memberRepository.save(members);
        return members;
    }

    public void memberDelete(Member member) {
        memberRepository.delete(member);
    }
}