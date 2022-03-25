package com.ssafy.b208.api.db.repository;

import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.db.entity.UserPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPokemonRepository extends JpaRepository<UserPokemon,Long> {
    Optional<UserPokemon> findUserPokemonByUser(User user);
}
