package com.ssafy.b208.api.controller;

import com.ssafy.b208.api.auth.NftUserDetail;
import com.ssafy.b208.api.db.entity.PokeDex;
import com.ssafy.b208.api.db.entity.UserPokemon;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.dto.response.*;
import com.ssafy.b208.api.service.PokedexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Api(value = "도감 API", tags = {"pokedex-controller"})
public class PokedexController {
    @Autowired
    private final PokedexService pokedexService;

    // 도감조회
    // (가진 포켓몬 번호 조회)
    // 원래 Request는 header에 jwtToken
    @GetMapping("/pokedex")
    @ApiOperation(value = "도감 조회", notes = "사용자의 도감을 조회한다.")
    public ResponseEntity<PokemonListDto> getPokemonList(Authentication authentication) throws Exception {
        NftUserDetail nftUserDetail = (NftUserDetail)authentication.getDetails();
        String email=nftUserDetail.getUsername();

        List<Long> pokemonList = pokedexService.getPokemonList(email);
        PokemonListDto pokemonListDto = PokemonListDto.builder()
                .pokemonList(pokemonList)
                .build();
        return ResponseEntity.status(200).body(pokemonListDto);
    }

    // 포켓몬 1마리 상세조회(도감 정보)
    @GetMapping("/pokedex/{pokedexId}")
    @ApiOperation(value = "포켓몬 도감 정보 상세 조회", notes = "pokedex id로 특정 포켓몬의 상세정보를 조회한다.")
    public ResponseEntity<PokeInfoOuterDto> getPokeInfo(@PathVariable Long pokedexId) throws Exception {

        PokeDex pokeDex = pokedexService.getPokeInfo(pokedexId);
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
        return ResponseEntity.status(200).body(pokeInfoOuterDto);
    }

    // 보유 NFP 리스트 by 한 포켓몬 조회
    @GetMapping("/nfp/{userId}/{pokedexId}")
    @ApiOperation(value = "특정 포켓몬의 보유 NFP 리스트 조회", notes = "user id와 pokedex id로 유저가 가진 특정 포켓몬의 NFP를 조회한다.")
    public ResponseEntity<NfpListDto> getNfpList(@PathVariable Long userId, @PathVariable Long pokedexId) throws Exception {

        List<NfpDetailDto> nfpList = pokedexService.getNfpList(userId, pokedexId);
        NfpListDto nfpListDto = NfpListDto.builder()
                .nfpList(nfpList)
                .build();
        return ResponseEntity.status(200).body(nfpListDto);
    }

    // NFP 상세조회
    //jwt
    @GetMapping("/nfp/detail/{tokenId}")
    @ApiOperation(value = "NFP 상세 조회", notes = "token id로 NFP의 상세정보를 조회한다.")
    public ResponseEntity<NfpDetailDto> getNfpDetail(@PathVariable Long tokenId) throws Exception {

        UserPokemon userPokemon = pokedexService.getNfpDetail(tokenId);
        NfpDetailDto nfpDetailDto = NfpDetailDto.builder()
                .tokenId(userPokemon.getTokenId())
                .pokedexId(userPokemon.getPokemon().getId())
                .ipfsMetaUri(userPokemon.getIpfsMetaUri())
                .ipfsImageUri(userPokemon.getIpfsImageUri())
                .grade(userPokemon.getGrade())
                .build();
        return ResponseEntity.status(200).body(nfpDetailDto);
    }

}