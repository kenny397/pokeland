package com.ssafy.b208.api.service;

import com.ssafy.b208.api.db.entity.PokeDex;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PokedexService {
    public List<Long> getPokemonList(UserRequestDto userRequestDto);

    public PokeDex getPokeInfo(Long id);
}
