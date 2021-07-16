package com.project.egloo.member.controller;

import com.project.egloo.member.domain.Member;
import com.project.egloo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public HashMap memberLogin(String userId, String password) {
        return memberService.memberLoginService(userId, password);
    }

    @PostMapping("/signup")
    public HashMap memberSignUp(@Valid Member member, Errors errors) {
        return memberService.memberSignUP(member, errors);
    }

}
