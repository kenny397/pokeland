package com.ssafy.b208.api.controller;


import com.ssafy.b208.api.dto.response.BaseResponseBody;
import com.ssafy.b208.api.dto.request.WalletRequestDto;
import com.ssafy.b208.api.service.GachaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/gacha")
@RequiredArgsConstructor
public class GachaController {

    private final GachaServiceImpl gachaService;

    @PostMapping()
    public ResponseEntity<BaseResponseBody>getNft(@RequestBody WalletRequestDto walletDto)throws Exception {
        gachaService.getNft(walletDto);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"뽑기 성공"));
    }


}
