package com.project.egloo.member.controller;

import com.project.egloo.common.AuthMember;
import com.project.egloo.member.domain.Member;
import com.project.egloo.member.dto.request.SignUpRequest;
import com.project.egloo.member.dto.response.MemberDTO;
import com.project.egloo.member.dto.response.SignUpResponse;
import com.project.egloo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/v1/users/me")
    public ResponseEntity<Object> getMyInfo(
        @AuthMember Member member
    ) {
        System.out.println(member);

        return ResponseEntity.ok(member);
    }

    @PostMapping("/api/v1/users/signup")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<SignUpResponse> memberSignUp(
        @Validated @RequestBody SignUpRequest signUpRequest
    ) {
        memberService.throwIfUserInfoNotUnique(signUpRequest.getUserId(), signUpRequest.getEmail());

        MemberDTO member = memberService.memberSignUP(signUpRequest);

        SignUpResponse signUpResponse = new SignUpResponse(member.getId());

        return ResponseEntity.ok(signUpResponse);
    }

}