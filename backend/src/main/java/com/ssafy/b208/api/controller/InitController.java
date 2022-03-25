package com.ssafy.b208.api.controller;


import com.ssafy.b208.api.db.entity.PokeDex;
import com.ssafy.b208.api.db.repository.PokeDexRepository;
import com.ssafy.b208.api.dto.response.BaseResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/init")
@RequiredArgsConstructor
public class InitController {

    private final PokeDexRepository pokeDexRepository;

    @PostMapping("/poketmon")
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

}
