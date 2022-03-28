package com.ssafy.b208.api.controller;

import com.ssafy.b208.api.db.entity.PokeDex;
import com.ssafy.b208.api.db.entity.UserPokemon;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.dto.response.NfpDetailDto;
import com.ssafy.b208.api.dto.response.PokeInfoDto;
import com.ssafy.b208.api.service.PokedexService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PokedexController {
    @Autowired
    private final PokedexService pokedexService;

    // 도감조회
    // (가진 포켓몬 번호 조회)
    // 원래 Request는 header에 jwtToken
    @GetMapping("/pokedex")
    public ResponseEntity<Map<String, List<Long>>> getPokemonList(HttpServletRequest req) throws Exception {
        String email = req.getParameter("email");
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        UserRequestDto userRequestDto = new UserRequestDto(email, nickname, password);
        List<Long> pokemonList = pokedexService.getPokemonList(userRequestDto);
        HashMap<String, List<Long>> pokeInfo = new HashMap<>();
        pokeInfo.put("pokeInfo", pokemonList);
        return ResponseEntity.status(200).body(pokeInfo);
    }

    // 포켓몬 1마리 상세조회(도감 정보)
    @GetMapping("/pokedex/{pokedexId}")
    public ResponseEntity<HashMap> getPokeInfo(@PathVariable Long pokedexId) throws Exception {

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
        HashMap<String, PokeInfoDto> pokeInfo = new HashMap<>();
        pokeInfo.put("pokeInfo", pokeInfoDto);
        return ResponseEntity.status(200).body(pokeInfo);
    }

    // NFP 상세조회
    @GetMapping("/nfp/detail/{tokenId}")
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
