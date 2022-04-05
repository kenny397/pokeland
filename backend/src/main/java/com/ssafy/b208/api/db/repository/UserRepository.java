package com.ssafy.b208.api.db.repository;

import com.ssafy.b208.api.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findOptionalByEmail(String email);

    Optional<User> findOptionalByNickname(String nickname);

    Optional<User> findOptionalByPublicKey(String publicKey);
}
