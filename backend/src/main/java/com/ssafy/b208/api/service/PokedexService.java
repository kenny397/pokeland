package com.ssafy.b208.api.service;

import com.ssafy.b208.api.db.entity.PokeDex;
import com.ssafy.b208.api.db.entity.UserPokemon;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.dto.response.NfpDetailDto;
import com.ssafy.b208.api.dto.response.NfpListDto;
import com.ssafy.b208.api.dto.response.PokeInfoOuterDto;
import com.ssafy.b208.api.dto.response.PokemonListDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PokedexService {
    public PokemonListDto getPokemonList(String email);

    public PokeInfoOuterDto getPokeInfo(Long id);

    public NfpListDto getNfpList(String publicKey, Long pokedexId);

    public NfpDetailDto getNfpDetail(Long id);
}
