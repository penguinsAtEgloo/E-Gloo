package com.project.egloo.member.controller;

import com.project.egloo.member.domain.Member;
import com.project.egloo.member.dto.request.SignUpRequest;
import com.project.egloo.member.dto.response.MemberDTO;
import com.project.egloo.member.dto.response.ProfileDTO;
import com.project.egloo.member.dto.response.SignUpResponse;
import com.project.egloo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {

    private final MemberService memberService;

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

    @GetMapping("/api/v1/users/me")
    public ResponseEntity<ProfileDTO> memberInfo(@AuthenticationPrincipal Member member) {
        ProfileDTO userProfile = memberService.memberInfo(member.getEmail());
        return ResponseEntity.ok(userProfile);
    }

    @PostMapping("/api/v1/users/update")
    public ResponseEntity<Member> memberUpdate(Member member) {
        Member members = memberService.memberUpdate(member);
        return ResponseEntity.ok(members);
    }

    @DeleteMapping("/api/v1/users/delete")
    public ResponseEntity<Member> memberDelete(Member member) {
        memberService.memberDelete(member);
        return ResponseEntity.ok(member);
    }
}