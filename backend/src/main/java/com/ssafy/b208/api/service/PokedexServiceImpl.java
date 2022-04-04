package com.ssafy.b208.api.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ssafy.b208.api.db.entity.PokeDex;
import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.db.entity.UserPokemon;
import com.ssafy.b208.api.db.repository.PokeDexRepository;
import com.ssafy.b208.api.db.repository.UserPokemonRepository;
import com.ssafy.b208.api.db.repository.UserRepository;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.dto.response.NfpDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PokedexServiceImpl implements PokedexService {
    @Autowired
    private UserPokemonRepository userPokemonRepository;
    @Autowired
    private PokeDexRepository pokeDexRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Long> getPokemonList(String email) {
        User user = Optional.ofNullable(userRepository.findOptionalByEmail(email).get())
                .orElseGet(() -> User.builder().build());
        List<Long> pokemonList = Optional.ofNullable(userPokemonRepository.findPokemonList(user.getId()).get())
                .orElseGet(() -> new ArrayList<>());
        System.out.println(pokemonList);
        return pokemonList;
    }

    @Override
    public PokeDex getPokeInfo(Long id) {
        PokeDex pokeDex = Optional.ofNullable(pokeDexRepository.findPokeDexById(id).get())
                .orElseGet(() -> new PokeDex());
        return pokeDex;
    }

    @Override
    public List<NfpDetailDto> getNfpList(String publicKey, Long pokedexId) {
        // public key로 userId 찾기
        User user = Optional.ofNullable(userRepository.findOptionalByPublicKey(publicKey).get())
                .orElseGet(() -> User.builder().build());
        Long userId = user.getId();

        List<UserPokemon> userPokemons = Optional.ofNullable(userPokemonRepository.findNfpList(userId, pokedexId).get())
                .orElseGet(() -> new ArrayList<>());
        List<NfpDetailDto> nftList= new ArrayList<>();
        for (UserPokemon userPokemon : userPokemons) {
            NfpDetailDto nfpDetailDto = NfpDetailDto.builder()
                    .tokenId(userPokemon.getTokenId())
                    .pokedexId(userPokemon.getPokemon().getId())
                    .ipfsMetaUri(userPokemon.getIpfsMetaUri())
                    .ipfsImageUri(userPokemon.getIpfsImageUri())
                    .grade(userPokemon.getGrade())
                    .build();
            nftList.add(nfpDetailDto);
        }
        return nftList;
    }

    @Override
    public UserPokemon getNfpDetail(Long id) {
        UserPokemon userPokemon = Optional.ofNullable(userPokemonRepository.findNfpDetail(id).get())
                .orElseGet(() -> new UserPokemon());
        return userPokemon;
    }

}
