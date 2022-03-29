package com.ssafy.b208.api.db.repository;

import com.ssafy.b208.api.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByNickname(String nickname);
}
