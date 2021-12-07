package com.example.board.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C000", "입력이 잘못 되었습니다."),
    METHOD_NOT_ALLOW(405, "C001", "지원하지 않는 메서드입니다."),

    // User
    NOT_FOUND_USER(400, "U000", "회원을 찾을 수 없습니다."),
    IDENTIFIER_DUPLICATION(400, "U001", "사용 중인 아이디입니다."),

    // Post
    NOT_FOUND_POST(400, "P000", "게시글을 찾을 수 없습니다.");

    private final int status;
    private final String message;
    private final String code;

    ErrorCode(int status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

}
