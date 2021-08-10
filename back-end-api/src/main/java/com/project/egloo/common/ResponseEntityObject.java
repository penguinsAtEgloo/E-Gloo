package com.project.egloo.common;

import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntityObject {
    private String code;
    private String msg;
    private String error;

    public static ResponseEntityObject of(ResponseEntityObject responseEntityObject) {
        return new ResponseEntityObject(responseEntityObject.getCode(), responseEntityObject.getMsg(), responseEntityObject.getError());
    }
}
