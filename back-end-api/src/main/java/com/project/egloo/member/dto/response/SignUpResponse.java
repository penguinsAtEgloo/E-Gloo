package com.project.egloo.member.dto.response;

import java.util.HashMap;

public class SignUpResponse {
    private int code;

    public SignUpResponse(int code) {
        this.code = code;
    }

    public HashMap response(Object msg){
        HashMap response = new HashMap();
        response.put("code",this.code);
        response.put("msg", msg);
        return response;
    }
}
