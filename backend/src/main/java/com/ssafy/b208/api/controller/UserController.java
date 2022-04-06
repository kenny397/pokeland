package com.ssafy.b208.api.controller;

import com.ssafy.b208.api.auth.JwtTokenUtil;
import com.ssafy.b208.api.auth.NftUserDetail;
import com.ssafy.b208.api.dto.UserDto;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.dto.response.BaseResponseBody;
import com.ssafy.b208.api.dto.response.CheckResponseDto;
import com.ssafy.b208.api.dto.response.UserLoginResponseDto;
import com.ssafy.b208.api.dto.response.UserMoneyResponseDto;
import com.ssafy.b208.api.exception.EmailNotFoundException;
import com.ssafy.b208.api.service.UserService;
import com.ssafy.b208.api.utils.InputValidation;
import com.ssafy.b208.api.utils.SiteURL;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletRequest;

@Api(value = "유저 API", tags = {"user-controller"})
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final InputValidation inputValidation;
    private final SiteURL siteURL;
    private final static String emailRegexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"; // by RFC 5322
    private final static String passWordRegexPattern = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).{6,15})"; // 숫자,소문자,대문자, 특문(!,@,#,$,%), 6~15 자리

    @ApiOperation(value = "회원가입", notes = "성공시 Success응답")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "실패 잘못된 요청"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("/register")
    public ResponseEntity<? extends BaseResponseBody> register(HttpServletRequest request, @RequestBody UserRequestDto userRequestDto) throws Exception {

        if(inputValidation.patternMatches(userRequestDto.getEmail(),emailRegexPattern) && inputValidation.patternMatches(userRequestDto.getPassword(),passWordRegexPattern)) {
            userService.register(userRequestDto, siteURL.getSiteURL("/register", request));
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
        }
        return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));

    }

    @ApiOperation(value = "메일인증", notes = "성공시 Success, 실패시 Fail 응답")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공 혹은 실패"),
            @ApiResponse(code = 401, message = "실패 아직 구현 x"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/verify")
    public ResponseEntity<? extends BaseResponseBody> verify(@RequestParam String code) throws Exception {
        if (userService.verify(code)) {
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
        } else {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Fail"));
        }
    }

    @ApiOperation(value = "로그인", notes = "로그인시 jwt토큰 Bearer형식과 지갑 publickey 주소 응답")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserRequestDto userRequestDto) throws Exception {
        String email = userRequestDto.getEmail();
        String password = userRequestDto.getPassword();
        UserDto userDto = userService.getUserByUserEmail(email);
        UserLoginResponseDto userloginResponseDto = new UserLoginResponseDto();
        try{
            if (passwordEncoder.matches(password, userDto.getPassword())) {
                if (userDto.isEnabled()) {
                    userloginResponseDto.setPublicKey(userDto.getPublicKey());
                    userloginResponseDto.setAccessToken(JwtTokenUtil.getToken(email));
                    userloginResponseDto.setVerified("Yes");
                } else {
                    userloginResponseDto.setVerified("No");
                }
            }
        }catch (Exception ex){
            throw new EmailNotFoundException("email");
        }
        return ResponseEntity.status(200).body(userloginResponseDto);
    }

    //자산, 유저가 가지고있는 NFT , 상세조회, 고객센터 email, 자산 조회 jwt
    @ApiOperation(value = "잔액조회", notes = "로그인한 회원 본인의 잔액정보를 응답한다. jwt토큰 필요")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/balance")
    public ResponseEntity<UserMoneyResponseDto> checkMoney(@ApiIgnore Authentication authentication) throws Exception {
        NftUserDetail nftUserDetail = (NftUserDetail) authentication.getDetails();
        String email = nftUserDetail.getUsername();
        UserDto userDto = userService.getUserByUserEmail(email);
        UserMoneyResponseDto userMoneyResponseDto = new UserMoneyResponseDto();
        userMoneyResponseDto.setMoney(userDto.getMoney());
        return ResponseEntity.status(200).body(userMoneyResponseDto);
    }

    @ApiOperation(value = "닉네임 중복검사", notes = "1은 이미 아이디가 있을때 0은 아이디가 없을때.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공 "),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/check/nickname/{nickname}")
    public ResponseEntity<CheckResponseDto> checkNickname(@PathVariable("nickname") String nickname) {
        CheckResponseDto checkResponseDto = new CheckResponseDto();
        UserDto user =userService.getUserByUserNickname(nickname);
        if (user != null) {
            checkResponseDto.setFlag(1L);
        } else {
            checkResponseDto.setFlag(0L);
        }
        return ResponseEntity.status(200).body(checkResponseDto);
    }

    @ApiOperation(value = "이메일 중복검사", notes = "1은 이미 아이디가 있을때 0은 아이디가 없을때.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공 1은 이미 아이디가 있을때 0은 아이디가 없을때"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/check/email/{email}")
    public ResponseEntity<CheckResponseDto> checkEmail(@PathVariable String email) {
        CheckResponseDto checkResponseDto = new CheckResponseDto();
        UserDto user = userService.getUserByUserEmail(email);
        if (user != null) {
            checkResponseDto.setFlag(1L);
        } else {
            checkResponseDto.setFlag(0L);
        }
        return ResponseEntity.status(200).body(checkResponseDto);
    }

    @ApiOperation(value = "미니게임 성공", notes = "성공한 아이디에 300SSF추가")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("/bonus")
    public ResponseEntity<UserMoneyResponseDto> giveBonus(@ApiIgnore Authentication authentication) {
        NftUserDetail nftUserDetail = (NftUserDetail) authentication.getDetails();
        String email = nftUserDetail.getUsername();
        UserDto userDto =   userService.giveBonus(email);
        UserMoneyResponseDto userMoneyResponseDto = new UserMoneyResponseDto();
        userMoneyResponseDto.setMoney(userDto.getMoney());
        return ResponseEntity.status(200).body(userMoneyResponseDto);

    }
}
