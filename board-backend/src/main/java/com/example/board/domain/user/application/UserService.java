package com.example.board.domain.user.application;

import com.example.board.common.error.ErrorCode;
import com.example.board.domain.user.dto.ProfileResponse;
import com.example.board.domain.user.dto.SignupRequest;
import com.example.board.domain.user.entity.User;
import com.example.board.domain.user.exception.DuplicateIdnetifierException;
import com.example.board.domain.user.exception.NotFoundUserException;
import com.example.board.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequest signupRequest) {
        validateIdentifier(signupRequest.getIdentifier());
        String encoded = passwordEncryption(signupRequest.getPassword());
        User user = User.builder()
                .name(signupRequest.getName())
                .identifier(signupRequest.getIdentifier())
                .password(encoded)
                .build();
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public ProfileResponse profile(String identifier) {
        User user = userRepository.findByIdentifier(identifier)
                .orElseThrow(() ->new NotFoundUserException(ErrorCode.NOT_FOUND_USER));
        return new ProfileResponse(user);
    }

    private void validateIdentifier(String identifier) {
        if (userRepository.existsByIdentifier(identifier)) {
            throw new DuplicateIdnetifierException(ErrorCode.IDENTIFIER_DUPLICATION);
        }
    }

    private String passwordEncryption(String password) {
        return passwordEncoder.encode(password);
    }

}
