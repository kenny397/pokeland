package com.ssafy.b208.api.service;

import com.ssafy.b208.api.dto.UserDto;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    public void register(UserRequestDto registerRequestDto);

    public UserDto getUserByUserEmail(String email);
}
