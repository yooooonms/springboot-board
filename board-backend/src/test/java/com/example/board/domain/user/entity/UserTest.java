package com.example.board.domain.user.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName(value = "회원 엔티티 테스트")
class UserTest {

    @Test
    @DisplayName(value = "회원 엔티티 생성 테스트")
    void user_entity_create() {
        User user = User.builder()
                .name("yoon")
                .identifier("yoonminsoo")
                .password("0000")
                .build();
        assertThat(user.getName()).isEqualTo("yoon");
        assertThat(user.getIdentifier()).isEqualTo("yoonminsoo");
        assertThat(user.getPassword()).isEqualTo("0000");
        assertThat(user.getRole()).isEqualTo(Role.USER);
    }

}