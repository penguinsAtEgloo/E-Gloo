package com.project.egloo.controller;

import com.project.egloo.domain.Member;
import com.project.egloo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public HashMap memberLogin(String userId, String password)  {
        return memberService.memberLoginService(userId, password);
    }

    @PostMapping("/signup")
    public HashMap memberSignUp(@Valid Member member, Errors errors) {
        return memberService.memberSignUP(member, errors);
    }

}
