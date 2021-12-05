package com.example.board.domain.user.dto;

import com.example.board.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileResponse {

    private String name;
    private String identifier;

    public ProfileResponse(User user) {
        this.name = user.getName();
        this.identifier = user.getIdentifier();
    }

}
