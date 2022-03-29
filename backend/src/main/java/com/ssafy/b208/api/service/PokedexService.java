package com.ssafy.b208.api.service;

import com.ssafy.b208.api.db.entity.PokeDex;
import com.ssafy.b208.api.db.entity.UserPokemon;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.dto.response.NfpDetailDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PokedexService {
    public List<Long> getPokemonList(String email);

    public PokeDex getPokeInfo(Long id);

    public List<NfpDetailDto> getNfpList(Long userId, Long pokedexId);

    public UserPokemon getNfpDetail(Long id);
}
