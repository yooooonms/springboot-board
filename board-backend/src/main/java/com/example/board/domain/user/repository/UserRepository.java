package com.example.board.domain.user.repository;

import com.example.board.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdentifier(String identifier);

    boolean existsByIdentifier(String identifier);

}
