package com.example.board.domain.post.entity;

import com.example.board.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName(value = "게시글 엔티티 테스트")
class PostTest {

    @Test
    @DisplayName(value = "게시글 엔티티 생성 테스트")
    void post_entity_create() {
        User user = User.builder()
                .name("yoon")
                .identifier("yoonminsoo")
                .build();
        Post post = Post.builder()
                .title("title")
                .content("content")
                .user(user)
                .build();
        assertThat(post.getTitle()).isEqualTo("title");
        assertThat(post.getWriter()).isEqualTo("yoonminsoo");
        assertThat(post.getContent()).isEqualTo("content");
        assertThat(post.getUser()).isEqualTo(user);
    }

}