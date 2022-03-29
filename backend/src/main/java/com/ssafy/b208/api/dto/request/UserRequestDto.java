package com.ssafy.b208.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDto {
    String email;
    String nickname;
    String password;
}
