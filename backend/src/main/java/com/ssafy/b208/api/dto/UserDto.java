package com.ssafy.b208.api.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    String email;
    String password;
    String publicKey;
    String privateKey;
    boolean enabled;
    Long Money;
}
