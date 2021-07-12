package com.project.egloo.service;

import com.project.egloo.common.StatusCode;
import com.project.egloo.domain.Member;
import com.project.egloo.dto.response.SignUpResponse;
import com.project.egloo.repository.MemberRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.HashMap;

@Service
public class MemberService {

    @Autowired
    MemberRespository memberRespository;

    public HashMap memberSignUP(Member member, Errors errors) {
        if (errors.hasErrors()) {
            HashMap response = new HashMap();
            for(int i=0; i < errors.getAllErrors().size(); i++) {
                response.put(errors.getFieldErrors().get(i).getField(), errors.getFieldErrors().get(i).getCode());
            }
            return new SignUpResponse(StatusCode.CLIENT_ERROR_BAD_REQUEST).response(response);
        }
        if (memberRespository.findByUserId(member.getUserId()) != null) {
            return new SignUpResponse(StatusCode.CLIENT_ERROR_CONFLICT).response("user already exist");
        }

        System.out.println(member);
        memberRespository.save(member);
        return new SignUpResponse(StatusCode.SUCCESS_OK).response("sign up success");
    }


    public HashMap memberLoginService(String userId, String password) {
        Member member = memberRespository.findByUserIdAndPassword(userId, password);
        if (member == null ) {
            return new SignUpResponse(StatusCode.CLIENT_ERROR_NOT_FOUND).response("user ID or password not found");
        }
        return new SignUpResponse(StatusCode.SUCCESS_OK).response("login success");
    }
}
