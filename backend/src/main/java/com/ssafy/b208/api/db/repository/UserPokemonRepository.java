package com.ssafy.b208.api.db.repository;

import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.db.entity.UserPokemon;
import com.ssafy.b208.api.dto.response.NfpDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserPokemonRepository extends JpaRepository<UserPokemon,Long> {

    List<UserPokemon> findUserPokemonByUserIsNull();
    @Query(value = "SELECT DISTINCT pokemon_id FROM user_pokemon WHERE user_id = :id", nativeQuery = true)
    Optional<List<Long>> findPokemonList(@Param("id") Long id);

//    @Query("SELECT u.pokemon FROM UserPokemon u WHERE u.user = :#{#user}")
//    List<Object[]> findPokemonList(@Param("user") User user);

    @Query(value = "SELECT * FROM user_pokemon WHERE user_id = :userId AND pokemon_id = :pokedexId", nativeQuery = true)
    Optional<List<UserPokemon>> findNfpList(@Param("userId") Long userId, @Param("pokedexId") Long pokedexId);

//    Optional<UserPokemon> findUserPokemonByTokenId(Long tokenId);

    @Query(value = "SELECT * FROM user_pokemon WHERE token_id = :tokenId", nativeQuery = true)
    Optional<UserPokemon> findNfpDetail(@Param("tokenId") Long id);



}
