package com.example.board.domain.user.application;

import com.example.board.domain.user.dto.ProfileResponse;
import com.example.board.domain.user.dto.SignupRequest;
import com.example.board.domain.user.entity.User;
import com.example.board.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName(value = "회원 서비스 테스트")
@ExtendWith(value = {MockitoExtension.class})
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName(value = "회원가입")
    void signup_test() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        SignupRequest signupRequest = new SignupRequest("name", "yoonmsinoo", "12345678");
        String encoded = encoder.encode(signupRequest.getPassword());
        User mockUser = User.builder()
                .name(signupRequest.getName())
                .identifier(signupRequest.getIdentifier())
                .password(encoded)
                .build();

        given(passwordEncoder.encode(any())).willReturn(encoded);
        given(userRepository.existsByIdentifier(any())).willReturn(false);
        given(userRepository.save(any())).willReturn(mockUser);

        userService.signup(signupRequest);

        then(passwordEncoder).should(times(1)).encode(any());
        then(userRepository).should(times(1)).existsByIdentifier(any());
        then(userRepository).should(times(1)).save(any());
    }

    @Test
    @DisplayName(value = "회원 프로필 조회")
    void profile_test() {
        User mockUser = User.builder()
                .name("yoon")
                .identifier("yoonminsoo")
                .build();

        given(userRepository.findByIdentifier(any())).willReturn(Optional.ofNullable(mockUser));

        ProfileResponse profile = userService.profile(any());

        assertThat(profile.getName()).isEqualTo(mockUser.getName());
        assertThat(profile.getIdentifier()).isEqualTo(mockUser.getIdentifier());
        then(userRepository).should(times(1)).findByIdentifier(any());

    }

}