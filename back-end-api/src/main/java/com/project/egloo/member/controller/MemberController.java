package com.project.egloo.member.controller;

import com.project.egloo.member.domain.Member;
import com.project.egloo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public Object memberSignUp(@Valid Member member, Errors errors) {
        return memberService.memberSignUP(member, errors);
    }

}