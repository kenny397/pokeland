package com.ssafy.b208.api.db.repository;

import com.ssafy.b208.api.db.entity.UserPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPokemonRepository extends JpaRepository<UserPokemon,Long> {
}
