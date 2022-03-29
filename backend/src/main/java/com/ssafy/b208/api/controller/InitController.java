package com.ssafy.b208.api.controller;


import com.ssafy.b208.api.db.entity.PokeDex;
import com.ssafy.b208.api.db.entity.UserPokemon;
import com.ssafy.b208.api.db.repository.PokeDexRepository;
import com.ssafy.b208.api.db.repository.UserPokemonRepository;
import com.ssafy.b208.api.dto.request.AttributesRequestDto;
import com.ssafy.b208.api.dto.request.PoketMonRequestDto;
import com.ssafy.b208.api.dto.response.BaseResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/init")
@RequiredArgsConstructor
public class InitController {

    private final PokeDexRepository pokeDexRepository;
    private final UserPokemonRepository userPokemonRepository;

    private final String jsonIpfs="https://gateway.pinata.cloud/ipfs/QmPXLaCos9u9SCDnwhyz8CHZnVpkkutJ4QfzCtKjtm6CcJ/";
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
