package com.ssafy.b208.api.controller;


import com.ssafy.b208.api.auth.NftUserDetail;
import com.ssafy.b208.api.dto.response.BaseResponseBody;
import com.ssafy.b208.api.dto.response.GachaResponseDto;
import com.ssafy.b208.api.service.GachaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/gacha")
@RequiredArgsConstructor
public class GachaController {

    private final GachaService gachaService;


    // spring security
    // ipfs반환하고 민팅정보 저장
    @PostMapping("")
    public ResponseEntity<GachaResponseDto> gacha(Authentication authentication){
        NftUserDetail nftUserDetail = (NftUserDetail)authentication.getDetails();
        String email=nftUserDetail.getUsername();



        GachaResponseDto gachaResponseDto= gachaService.gacha(email);
        return ResponseEntity.status(200).body(gachaResponseDto);
    }



}
