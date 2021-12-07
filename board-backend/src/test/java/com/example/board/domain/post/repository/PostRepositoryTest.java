package com.example.board.domain.post.repository;

import com.example.board.domain.post.entity.Post;
import com.example.board.domain.user.entity.User;
import com.example.board.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DisplayName(value = "게시글 리포지토리 테스트")
@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private User createUser() {
        User user = User.builder()
                .identifier("yoonminsoo")
                .build();
        return userRepository.save(user);
    }

    @Test
    @DisplayName(value = "게시글 저장")
    void post_save_test() {
        User user = createUser();
        Post post = Post.builder()
                .title("title")
                .content("content")
                .user(user)
                .build();

        Post save = postRepository.save(post);

        assertThat(save.getTitle()).isEqualTo("title");
        assertThat(save.getWriter()).isEqualTo("yoonminsoo");
        assertThat(save.getContent()).isEqualTo("content");
    }

    @Test
    @DisplayName(value = "게시글 조회")
    void post_findById_test() {
        User user = createUser();
        Post post = Post.builder()
                .title("title")
                .content("content")
                .user(user)
                .build();
        Post save = postRepository.save(post);

        Post find = postRepository.findById(save.getId()).orElse(null);

        assertThat(find).isNotNull();
        assertThat(find.getTitle()).isEqualTo("title");
        assertThat(find.getWriter()).isEqualTo("yoonminsoo");
        assertThat(find.getContent()).isEqualTo("content");
    }

}