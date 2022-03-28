package com.ssafy.b208.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    String email;
    String nickname;
    String password;
}
