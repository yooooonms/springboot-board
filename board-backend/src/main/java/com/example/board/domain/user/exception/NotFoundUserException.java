package com.example.board.domain.user.exception;

import com.example.board.common.error.ErrorCode;
import com.example.board.common.error.exception.BoardException;

public class NotFoundUserException extends BoardException {

    public NotFoundUserException(ErrorCode errorCode) {
        super(errorCode);
    }

}
