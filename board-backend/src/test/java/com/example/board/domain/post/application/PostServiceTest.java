package com.example.board.domain.post.application;

import com.example.board.domain.post.dto.PostDetailResponse;
import com.example.board.domain.post.dto.PostModifyRequest;
import com.example.board.domain.post.dto.PostWriteRequest;
import com.example.board.domain.post.entity.Post;
import com.example.board.domain.post.repository.PostRepository;
import com.example.board.domain.user.entity.User;
import com.example.board.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName(value = "게시글 서비스 테스트")
@ExtendWith(value = MockitoExtension.class)
class PostServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private User mockUser() {
        return User.builder()
                .identifier("yoonminsoo")
                .build();
    }

    @Test
    @DisplayName(value = "게시글 등록")
    void post_write_test() {
        User mockUser = mockUser();
        PostWriteRequest postWriteRequest = new PostWriteRequest("title", "content");
        Post mockPost = Post.builder()
                .title(postWriteRequest.getTitle())
                .content(postWriteRequest.getContent())
                .user(mockUser)
                .build();

        ReflectionTestUtils.setField(mockPost, "id", 1L);
        given(userRepository.findByIdentifier(any())).willReturn(Optional.ofNullable(mockUser));
        given(postRepository.save(any())).willReturn(mockPost);

        Long postId = postService.postWrite(any(), postWriteRequest);

        assertThat(postId).isEqualTo(1L);
        then(userRepository).should(times(1)).findByIdentifier(any());
        then(postRepository).should(times(1)).save(any());
    }

    @Test
    @DisplayName(value = "게시글 조회")
    void post_detail_test() {
        Post mockPost = Post.builder()
                .title("title")
                .content("content")
                .user(User.builder().identifier("yoonminsoo").build())
                .build();

        given(postRepository.findById(any())).willReturn(Optional.ofNullable(mockPost));

        PostDetailResponse postDetailResponse = postService.postDetail(any());

        assertThat(postDetailResponse.getTitle()).isEqualTo("title");
        assertThat(postDetailResponse.getWriter()).isEqualTo("yoonminsoo");
        assertThat(postDetailResponse.getContent()).isEqualTo("content");
        then(postRepository).should(times(1)).findById(any());
    }

    @Test
    @DisplayName(value = "게시글 수정")
    void post_modify_test() {
        Post mockPost = Post.builder()
                .title("title")
                .content("content")
                .user(User.builder().identifier("yoonminsoo").build())
                .build();
        PostModifyRequest postModifyRequest = new PostModifyRequest("mTitle", "mContent");

        given(postRepository.findById(any())).willReturn(Optional.ofNullable(mockPost));

        PostDetailResponse postDetailResponse = postService.postModify(any(), postModifyRequest);

        assertThat(postDetailResponse.getTitle()).isEqualTo("mTitle");
        assertThat(postDetailResponse.getWriter()).isEqualTo("yoonminsoo");
        assertThat(postDetailResponse.getContent()).isEqualTo("mContent");
        then(postRepository).should(times(1)).findById(any());
    }


}