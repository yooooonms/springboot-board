package com.example.board.domain.post.application;

import com.example.board.common.error.ErrorCode;
import com.example.board.domain.post.dto.PostDetailResponse;
import com.example.board.domain.post.dto.PostModifyRequest;
import com.example.board.domain.post.dto.PostWriteRequest;
import com.example.board.domain.post.entity.Post;
import com.example.board.domain.post.exception.NotFoundPostException;
import com.example.board.domain.post.repository.PostRepository;
import com.example.board.domain.user.entity.User;
import com.example.board.domain.user.exception.NotFoundUserException;
import com.example.board.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long postWrite(String identifier, PostWriteRequest postWriteRequest) {
        User user = userRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER));
        Post post = Post.builder()
                .title(postWriteRequest.getTitle())
                .content(postWriteRequest.getContent())
                .user(user)
                .build();
        return postRepository.save(post).getId();
    }

    @Transactional(readOnly = true)
    public PostDetailResponse postDetail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundPostException(ErrorCode.NOT_FOUND_POST));
        return new PostDetailResponse(post);
    }

    @Transactional
    public PostDetailResponse postModify(Long postId, PostModifyRequest postModifyRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundPostException(ErrorCode.NOT_FOUND_POST));
        post.modify(postModifyRequest.getTitle(), postModifyRequest.getContent());
        return new PostDetailResponse(post);
    }

}
