package com.example.board.common.error.exception;

import com.example.board.common.error.ErrorCode;

public class BoardException extends RuntimeException {

    private ErrorCode errorCode;

    public BoardException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
