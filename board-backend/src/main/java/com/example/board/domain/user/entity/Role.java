package com.example.board.domain.user.entity;

import lombok.Getter;

@Getter
public enum Role {

    USER("USER", "회원"),
    ADMIN("ADMIN", "관리자");

    private final String key;
    private final String value;

    Role(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
