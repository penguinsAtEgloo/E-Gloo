package com.project.egloo.member.controller;

import com.project.egloo.member.dto.request.SignUpRequest;
import com.project.egloo.member.dto.response.SignUpResponse;
import com.project.egloo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<SignUpResponse> memberSignUp(@Valid SignUpRequest signUpRequest, Errors errors) throws Exception {
        SignUpResponse signUpResponse = memberService.memberSignUP(signUpRequest, errors);
        return ResponseEntity.ok(signUpResponse);
    }

}