package com.ssafy.b208.api.controller;


import com.ssafy.b208.api.auth.NftUserDetail;
import com.ssafy.b208.api.dto.response.BaseResponseBody;
import com.ssafy.b208.api.dto.response.GachaResponseDto;
import com.ssafy.b208.api.service.GachaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "뽑기페이지 API", tags = {"gacha-controller"})
@RestController
@RequestMapping("/api/v1/gacha")
@RequiredArgsConstructor
public class GachaController {

    private final GachaService gachaService;


    // spring security
    // ipfs반환하고 민팅정보 저장
    @ApiOperation(value = "nft 뽑기", notes = "성공시 민팅 주소 응답 jwt토큰 필요")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패 아직 구현 x"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("")
    public ResponseEntity<GachaResponseDto> gacha(@ApiIgnore Authentication authentication){
        NftUserDetail nftUserDetail = (NftUserDetail)authentication.getDetails();
        String email=nftUserDetail.getUsername();



        GachaResponseDto gachaResponseDto= gachaService.gacha(email);
        return ResponseEntity.status(200).body(gachaResponseDto);
    }



}
