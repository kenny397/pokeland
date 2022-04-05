package com.ssafy.b208.api.controller;

import com.ssafy.b208.api.auth.NftUserDetail;
import com.ssafy.b208.api.db.entity.PokeDex;
import com.ssafy.b208.api.db.entity.UserPokemon;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.dto.response.*;
import com.ssafy.b208.api.service.PokedexService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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

    private final PokedexService pokedexService;

    // 도감조회
    // (가진 포켓몬 번호 조회)
    // 원래 Request는 header에 jwtToken
    @GetMapping("/pokedex")
    @ApiOperation(value = "도감 조회", notes = "응답으로 사용자가 가진 포켓몬의 pokedex_id들이 담긴 리스트가 나온다. jwt토큰 필요")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패 아직 구현 x"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<PokemonListDto> getPokemonList(@ApiIgnore Authentication authentication) throws Exception {

        NftUserDetail nftUserDetail = (NftUserDetail) authentication.getDetails();
        String email = nftUserDetail.getUsername();

        PokemonListDto pokemonListDto = pokedexService.getPokemonList(email);
        return ResponseEntity.status(200).body(pokemonListDto);
    }

    // 포켓몬 1마리 상세조회(도감 정보)
    @GetMapping("/pokedex/{pokedexId}")
    @ApiOperation(value = "포켓몬 도감 정보 상세 조회", notes = "pokedex id로 특정 포켓몬의 상세정보를 조회한다.")
    @ApiImplicitParam(name = "pokedexId", value = "포켓몬 번호", required = true)
    public ResponseEntity<PokeInfoOuterDto> getPokeInfo(@PathVariable Long pokedexId) throws Exception {

        PokeInfoOuterDto pokeInfoOuterDto = pokedexService.getPokeInfo(pokedexId);
        return ResponseEntity.status(200).body(pokeInfoOuterDto);
    }

    // 보유 NFP 리스트 by 한 포켓몬 조회
    @GetMapping("/nfp/{publicKey}/{pokedexId}")
    @ApiOperation(value = "특정 포켓몬의 보유 NFP 리스트 조회", notes = "public key와 pokedex id로 유저가 가진 특정 포켓몬의 NFP들을 조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pokedexId", value = "포켓몬 번호", required = true),
        @ApiImplicitParam(name = "publicKey", value = "공개키", required = true)
    })
    public ResponseEntity<NfpListDto> getNfpList(@PathVariable String publicKey, @PathVariable Long pokedexId) throws Exception {

        NfpListDto nfpListDto = pokedexService.getNfpList(publicKey, pokedexId);
        return ResponseEntity.status(200).body(nfpListDto);
    }

    // NFP 상세조회
    //jwt
    @GetMapping("/nfp/detail/{tokenId}")
    @ApiOperation(value = "NFP 상세 조회", notes = "token id로 NFP의 상세정보를 조회한다.")
    @ApiImplicitParam(name = "tokenId", value = "토큰 아이디", required = true)
    public ResponseEntity<NfpDetailDto> getNfpDetail(@PathVariable Long tokenId) throws Exception {

        NfpDetailDto nfpDetailDto = pokedexService.getNfpDetail(tokenId);
        return ResponseEntity.status(200).body(nfpDetailDto);
    }

}