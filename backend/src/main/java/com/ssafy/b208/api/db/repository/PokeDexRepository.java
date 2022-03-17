package com.ssafy.b208.api.db.repository;

import com.ssafy.b208.api.db.entity.PokeDex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokeDexRepository extends JpaRepository<PokeDex,Long> {
}
