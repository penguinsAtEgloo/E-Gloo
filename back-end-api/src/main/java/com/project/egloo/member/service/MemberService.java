package com.project.egloo.member.service;

import com.project.egloo.common.ResponseEntityObject;
import com.project.egloo.common.StatusCode;
import com.project.egloo.common.exceptions.ErrorCode;
import com.project.egloo.member.domain.Member;
import com.project.egloo.member.dto.response.SignUpResponse;
import com.project.egloo.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRespository;

    public Object memberSignUP(Member member, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntityObject(ErrorCode.BAD_REGISTER.getCode(),"",ErrorCode.BAD_REGISTER.getMessage());
        }
        if (memberRespository.findByUserId(member.getUserId()) != null) {
            return new ResponseEntityObject(ErrorCode.DUPLICATED_EMAIL.getCode(),"",ErrorCode.DUPLICATED_EMAIL.getMessage());
        }
        memberRespository.save(member);
        return new ResponseEntityObject(ErrorCode.SUCCESS.getCode(),ErrorCode.SUCCESS.getMessage(),"");
    }


    public Object memberLoginService(String userId, String password) {
        Optional<Member> member = memberRespository.findByUserIdAndPassword(userId, password);
        if (member == null ) {
            return new ResponseEntityObject(ErrorCode.UNAUTHORIZED_MEMBER.getCode(),"",ErrorCode.UNAUTHORIZED_MEMBER.getMessage());
        }
        return new ResponseEntityObject(ErrorCode.SUCCESS.getCode(),ErrorCode.SUCCESS.getMessage(),"");
    }
}
