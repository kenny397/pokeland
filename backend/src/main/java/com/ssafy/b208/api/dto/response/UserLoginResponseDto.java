package com.ssafy.b208.api.dto.response;

import com.ssafy.b208.api.db.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponseDto {
    String accessToken;
    String publicKey;

}
