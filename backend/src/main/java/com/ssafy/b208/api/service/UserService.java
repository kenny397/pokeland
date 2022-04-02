package com.ssafy.b208.api.service;

import com.ssafy.b208.api.dto.UserDto;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.exception.ExistIdException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    public void register(UserRequestDto registerRequestDto) throws ExistIdException;

    public UserDto getUserByUserEmail(String email);

    UserDto getUserByUserNickname(String nickname);
}
