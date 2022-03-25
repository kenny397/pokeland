package com.ssafy.b208.api.controller;


import com.ssafy.b208.api.dto.UserDto;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.dto.response.BaseResponseBody;
import com.ssafy.b208.api.dto.response.PublicWalletResponseDto;
import com.ssafy.b208.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<? extends BaseResponseBody> register(@RequestBody UserRequestDto userRequestDto)throws Exception {
        userService.register(userRequestDto);
        //있는 email이면 에러 발생 시켜야됨
        //회원가입 email인증

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @PostMapping("/login")
    public ResponseEntity<PublicWalletResponseDto> login(@RequestBody UserRequestDto userRequestDto)throws Exception {
        String email = userRequestDto.getEmail();
        String password = userRequestDto.getPassword();
        UserDto userDto =userService.getUserByUserEmail(email);
        //맞으면 토큰도 줘야함
        PublicWalletResponseDto publicWalletResponseDto= new PublicWalletResponseDto();
        publicWalletResponseDto.setPublicKey(userDto.getPublicKey());
        //틀리면 에러
        return ResponseEntity.status(200).body(publicWalletResponseDto);
    }
    //자산, 유저가 가지고있는 NFT , 상세조회 , 고객센터 email







}
