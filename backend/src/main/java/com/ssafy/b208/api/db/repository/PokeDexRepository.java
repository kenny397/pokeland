package com.ssafy.b208.api.db.repository;

import com.ssafy.b208.api.db.entity.PokeDex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PokeDexRepository extends JpaRepository<PokeDex,Long> {
    Optional<PokeDex> findPokeDexById(Long id);
}
