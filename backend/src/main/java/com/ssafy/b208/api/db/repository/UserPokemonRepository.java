package com.ssafy.b208.api.db.repository;

import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.db.entity.UserPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserPokemonRepository extends JpaRepository<UserPokemon,Long> {
    Optional<UserPokemon> findUserPokemonByUser(User user);

    @Query(value = "SELECT DISTINCT pokemon_id FROM user_pokemon WHERE user_id = :id", nativeQuery = true)
    Optional<List<Long>> findPokemonList2(@Param("id") Long id);

//    @Query("SELECT u.pokemon FROM UserPokemon u WHERE u.user = :#{#user}")
//    List<Object[]> findPokemonList(@Param("user") User user);
}
