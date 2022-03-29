package com.ssafy.b208.api.controller;


import com.ssafy.b208.api.db.entity.PokeDex;
import com.ssafy.b208.api.db.entity.UserPokemon;
import com.ssafy.b208.api.db.repository.PokeDexRepository;
import com.ssafy.b208.api.db.repository.UserPokemonRepository;
import com.ssafy.b208.api.dto.request.AttributesRequestDto;
import com.ssafy.b208.api.dto.request.PoketMonRequestDto;
import com.ssafy.b208.api.dto.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "초기 설정 API", tags = {"init-controller"})
@RestController
@RequestMapping("/api/v1/init")
@RequiredArgsConstructor
public class InitController {

    private final PokeDexRepository pokeDexRepository;
    private final UserPokemonRepository userPokemonRepository;

    private final String jsonIpfs="https://gateway.pinata.cloud/ipfs/QmPXLaCos9u9SCDnwhyz8CHZnVpkkutJ4QfzCtKjtm6CcJ/";

    @ApiOperation(value = "pokedex 초기 업로드")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("/pokemon")
    public ResponseEntity<BaseResponseBody>setPokeDex(@RequestBody PokeDex[] pokeDexs){
        try{
            for(int i=0; i<pokeDexs.length; i++){
                pokeDexRepository.save(pokeDexs[i]);
            }
            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"good"));
        }catch (Exception e) {
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "bad"));
        }
    }
    @ApiOperation(value = "ipfs 초기 업로드")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("/ipfs")
    public ResponseEntity<BaseResponseBody>setUserPoke(@RequestBody PoketMonRequestDto[] poketMonRequestDto){
        try {
            for(int i=0; i<poketMonRequestDto.length; i++){
                AttributesRequestDto[] attrubute=poketMonRequestDto[i].getAttributes();
                String rank=attrubute[0].getValue();
                PokeDex pokeDex=pokeDexRepository.findById(Long.parseLong(attrubute[1].getValue())).get();
                UserPokemon userPokemon=new UserPokemon();
                userPokemon.setPokemon(pokeDex);
                userPokemon.setIpfsImageUri(poketMonRequestDto[i].getImage());
                userPokemon.setIpfsMetaUri(jsonIpfs+i+".json");
                userPokemon.setGrade(rank);
                userPokemonRepository.save(userPokemon);
            }
            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"good"));

        }catch (Exception e){
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "bad"));


        }
    }
}
