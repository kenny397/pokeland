package com.ssafy.b208.api.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    String email;
    String password;
    String publicKey;
    String privateKey;
    Long Money;
}
