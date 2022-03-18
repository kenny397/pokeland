package com.ssafy.b208.api.db.repository;

import com.ssafy.b208.api.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
