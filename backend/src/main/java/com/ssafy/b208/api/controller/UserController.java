package com.ssafy.b208.api.controller;


import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.dto.UserDto;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.dto.response.BaseResponseBody;
import com.ssafy.b208.api.dto.response.UserLoginResponseDto;
import com.ssafy.b208.api.dto.response.UserMoneyResponseDto;
import com.ssafy.b208.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<? extends BaseResponseBody> register(@RequestBody UserRequestDto userRequestDto)throws Exception {
        userService.register(userRequestDto);
        //있는 email이면 에러 발생 시켜야됨
        //회원가입 email인증

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserRequestDto userRequestDto)throws Exception {
        String email = userRequestDto.getEmail();
        String password = userRequestDto.getPassword();
        UserDto userDto =userService.getUserByUserEmail(email);

        UserLoginResponseDto userloginResponseDto= new UserLoginResponseDto();
        if(passwordEncoder.matches(password, userDto.getPassword())){
            userloginResponseDto.setPublicKey(userDto.getPublicKey());
            return ResponseEntity.status(200).body(userloginResponseDto);
        }else{
            return ResponseEntity.status(401).body(userloginResponseDto);
        }

    }
    //자산, 유저가 가지고있는 NFT , 상세조회 , 고객센터 email
    // 자산 조회
    @GetMapping("/money")
    public ResponseEntity<UserMoneyResponseDto> checkMoney(@RequestBody UserRequestDto userRequestDto) throws Exception {
        String email = userRequestDto.getEmail();
        UserDto userDto = userService.getUserByUserEmail(email);
        UserMoneyResponseDto userMoneyResponseDto = new UserMoneyResponseDto();
        userMoneyResponseDto.setMoney(userDto.getMoney());
        return ResponseEntity.status(200).body(userMoneyResponseDto);
    }

//    // 가진 NFT 조회
//    @GetMapping("/nft")
//    public ResponseEntity<> getNft(@RequestBody UserRequestDto userRequestDto) throws Exception {
//        String email = userRequestDto.getEmail();
//        userService.getUserByUserEmail(email);
//    }






}
