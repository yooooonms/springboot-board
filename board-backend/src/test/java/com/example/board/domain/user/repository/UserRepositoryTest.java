package com.example.board.domain.user.repository;

import com.example.board.domain.user.entity.Role;
import com.example.board.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DisplayName(value = "회원 리포지토리 테스트")
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User createUser() {
        return User.builder()
                .name("name")
                .identifier("yoonminsoo")
                .password("0000")
                .build();
    }

    @Test
    @DisplayName(value = "회원 저장 테스트")
    void user_save_test() {
        User user = createUser();

        User save = userRepository.save(user);

        assertThat(save).isNotNull();
        assertThat(save.getName()).isEqualTo("name");
        assertThat(save.getIdentifier()).isEqualTo("yoonminsoo");
        assertThat(save.getPassword()).isEqualTo("0000");
        assertThat(save.getRole()).isEqualTo(Role.USER);
    }

    @Test
    @DisplayName(value = "기본키로 회원 조회")
    void user_findById_test() {
        User user = createUser();
        User save = userRepository.save(user);

        User find = userRepository.findById(save.getId()).orElse(null);

        assertThat(find).isNotNull();
        assertThat(save.getName()).isEqualTo("name");
        assertThat(save.getIdentifier()).isEqualTo("yoonminsoo");
        assertThat(save.getPassword()).isEqualTo("0000");
        assertThat(save.getRole()).isEqualTo(Role.USER);
    }

    @Test
    @DisplayName(value = "아이디로 회원 조회")
    void user_findByIdentifier_test() {
        User user = createUser();
        User save = userRepository.save(user);

        User find = userRepository.findByIdentifier(save.getIdentifier()).orElse(null);

        assertThat(find).isNotNull();
        assertThat(save.getName()).isEqualTo("name");
        assertThat(save.getIdentifier()).isEqualTo("yoonminsoo");
        assertThat(save.getPassword()).isEqualTo("0000");
        assertThat(save.getRole()).isEqualTo(Role.USER);
    }

    @Test
    @DisplayName(value = "아이디 존재 여부")
    void user_existsByIdentifier_test() {
        User user = createUser();
        User save = userRepository.save(user);

        boolean exists = userRepository.existsByIdentifier(save.getIdentifier());

        assertThat(exists).isTrue();
    }

}