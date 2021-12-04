package com.example.board.domain.user.exception;

import com.example.board.common.error.ErrorCode;
import com.example.board.common.error.exception.BoardException;

public class DuplicateIdnetifierException extends BoardException {

    public DuplicateIdnetifierException(ErrorCode errorCode) {
        super(errorCode);
    }

}
