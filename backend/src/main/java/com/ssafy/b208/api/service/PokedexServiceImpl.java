package com.ssafy.b208.api.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ssafy.b208.api.db.entity.PokeDex;
import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.db.entity.UserPokemon;
import com.ssafy.b208.api.db.repository.PokeDexRepository;
import com.ssafy.b208.api.db.repository.UserPokemonRepository;
import com.ssafy.b208.api.db.repository.UserRepository;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.dto.response.*;
import com.ssafy.b208.api.exception.NonExistentPokedexIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PokedexServiceImpl implements PokedexService {

    private UserPokemonRepository userPokemonRepository;
    private PokeDexRepository pokeDexRepository;
    private UserRepository userRepository;

    @Override
    public PokemonListDto getPokemonList(String email) {
        User user = Optional.ofNullable(userRepository.findOptionalByEmail(email).get())
                .orElseGet(() -> User.builder().build());
        List<Long> pokemonList = Optional.ofNullable(userPokemonRepository.findPokemonList(user.getId()).get())
                .orElseGet(() -> new ArrayList<>());
        PokemonListDto pokemonListDto = PokemonListDto.builder()
                .pokemonList(pokemonList)
                .build();
        return pokemonListDto;
    }

    @Override
    public PokeInfoOuterDto getPokeInfo(Long id) {
        try {
            log.info("id : " + id);
            PokeDex pokeDex =
                    Optional.ofNullable(pokeDexRepository.findPokeDexById(id).get())
                    .orElseGet(() -> new PokeDex());
            log.info("id2 : " + id);
            PokeInfoDto pokeInfoDto = PokeInfoDto.builder()
                    .id(pokeDex.getId())
                    .name(pokeDex.getName())
                    .type(pokeDex.getType())
                    .height(pokeDex.getHeight())
                    .category(pokeDex.getCategory())
                    .gender(pokeDex.getGender())
                    .weight(pokeDex.getWeight())
                    .abilities(pokeDex.getAbilities())
                    .build();
            PokeInfoOuterDto pokeInfoOuterDto = PokeInfoOuterDto.builder()
                    .pokeInfo(pokeInfoDto)
                    .build();
            return pokeInfoOuterDto;
        } catch (NullPointerException e) {
            throw new NonExistentPokedexIdException(id);
        }
    }

    @Override
    public NfpListDto getNfpList(String publicKey, Long pokedexId) {
        // public key로 userId 찾기
        User user = Optional.ofNullable(userRepository.findOptionalByPublicKey(publicKey).get())
                .orElseGet(() -> User.builder().build());
        Long userId = user.getId();

        List<UserPokemon> userPokemons = Optional.ofNullable(userPokemonRepository.findNfpList(userId, pokedexId).get())
                .orElseGet(() -> new ArrayList<>());
        List<NfpDetailDto> nfpList= new ArrayList<>();
        for (UserPokemon userPokemon : userPokemons) {
            NfpDetailDto nfpDetailDto = NfpDetailDto.builder()
                    .tokenId(userPokemon.getTokenId())
                    .pokedexId(userPokemon.getPokemon().getId())
                    .ipfsMetaUri(userPokemon.getIpfsMetaUri())
                    .ipfsImageUri(userPokemon.getIpfsImageUri())
                    .grade(userPokemon.getGrade())
                    .build();
            nfpList.add(nfpDetailDto);
        }
        NfpListDto nfpListDto = NfpListDto.builder()
                .nfpList(nfpList)
                .build();
        return nfpListDto;
    }

    @Override
    public NfpDetailDto getNfpDetail(Long id) {
        UserPokemon userPokemon = Optional.ofNullable(userPokemonRepository.findNfpDetail(id).get())
                .orElseGet(() -> new UserPokemon());
        NfpDetailDto nfpDetailDto = NfpDetailDto.builder()
                .tokenId(userPokemon.getTokenId())
                .pokedexId(userPokemon.getPokemon().getId())
                .ipfsMetaUri(userPokemon.getIpfsMetaUri())
                .ipfsImageUri(userPokemon.getIpfsImageUri())
                .grade(userPokemon.getGrade())
                .build();
        return nfpDetailDto;
    }

}
