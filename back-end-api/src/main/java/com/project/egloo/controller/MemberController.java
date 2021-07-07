package com.project.egloo.controller;

import com.project.egloo.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public int memberLogin(String userId, String password)  {
        int statusCode = memberService.memberLoginService(userId, password);
        return statusCode;
    }
}
