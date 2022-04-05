package com.ssafy.b208.api.service;

import com.ssafy.b208.api.dto.UserDto;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.exception.ExistIdException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public interface UserService {
    public void register(UserRequestDto registerRequestDto, String siteURL)
            throws MessagingException, UnsupportedEncodingException, ExistIdException;

    public UserDto getUserByUserEmail(String email);

    UserDto getUserByUserNickname(String nickname);

    public boolean verify(String verificationCode);

}

