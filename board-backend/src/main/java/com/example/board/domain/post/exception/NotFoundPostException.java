package com.example.board.domain.post.exception;

import com.example.board.common.error.ErrorCode;
import com.example.board.common.error.exception.BoardException;

public class NotFoundPostException extends BoardException {

    public NotFoundPostException(ErrorCode errorCode) {
        super(errorCode);
    }

}
