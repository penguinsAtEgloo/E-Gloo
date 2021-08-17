package com.project.egloo.common.exceptions;

import lombok.Getter;


@Getter
public enum ErrorCode {

//    server
    INTERNAL_SERVER_ERROR(500, "C_001", "서버가 터졌습니다."),
    METHOD_NOT_ALLOWED(405, "C_002", "Api는 열려있으나 메소드는 사용 불가합니다."),
    INVALID_INPUT_VALUE(400, "C_003", "적절하지 않은 요청 값입니다."),
    INVALID_TYPE_VALUE(400, "C_004", "요청 값의 타입이 잘못되었습니다."),
    ENTITY_NOT_FOUND(400, "C_005", "지정한 Entity를 찾을 수 없습니다."),

//    auth
    SUCCESS(200,"AU_000","요청에 성공하였습니다."),
    AUTH_ERROR(400, "AU_001", "인증 관련 오류가 발생했습니다."),
    DUPLICATED_EMAIL(400, "AU_002", "이미 존재하는 아이디입니다."),
    DUPLICATED_ID(400, "AU_003", "이미 존재하는 아이디입니다."),
    UNAUTHORIZED_REDIRECT_URI(400, "AU_004", "인증되지 않은 REDIRECT_URI입니다."),
    BAD_LOGIN(400, "AU_005", "잘못된 아이디 또는 패스워드입니다."),
    UNAUTHORIZED_MEMBER(400,"AU_006","존재하지않는 유저입니다."),
    CHECK_REVIEW(200,"AU_008","이미 작성된 리뷰가 있습니다."),
    CHECK_ANSWER(200,"AU_009","이미 작성된 답글가 있습니다."),
    NOT_FOUND_IMAGE(400,"AU_007","이미지 관련 오류가 발생했습니다."),

//    register
    BAD_REGISTER(400,"RG_001","회원가입에 필수적으로 필요한 데이터가 충족되지 않았습니다."),

//    recipe
    BAD_RECIPE(400 ,"RC_001","필수 레시피 데이터 값이 입력되지 않았습니다."),
    DUPLICATED_RECIPE(400,"RE_002","이미 존재하는 레시피입니다.");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
