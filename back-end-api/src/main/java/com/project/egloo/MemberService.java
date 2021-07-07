package com.project.egloo;

import com.project.egloo.common.StatusCode;
import com.project.egloo.domain.Member;
import com.project.egloo.repository.MemberRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRespository memberRespository;

    public int memberLoginService(String userId, String password) {
        Member member = memberRespository.findByUserIdAndPassword(userId, password);
        if (member == null ) { return StatusCode.CLIENT_ERROR_NOT_FOUND;}
        return StatusCode.SUCCESS_OK;
    }
}
