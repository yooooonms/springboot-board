package com.example.board.domain.post.entity;

import com.example.board.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName(value = "게시글 엔티티 테스트")
class PostTest {

    @Test
    @DisplayName(value = "게시글 엔티티 생성")
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

    @Test
    @DisplayName("게시글 엔티티 수정")
    void post_modify_test() {
        Post post = Post.builder()
                .title("title")
                .content("content")
                .user(User.builder().identifier("yoonminsoo").build())
                .build();

        post.modify("mTitle", "mContent");

        assertThat(post.getTitle()).isEqualTo("mTitle");
        assertThat(post.getContent()).isEqualTo("mContent");
    }

}