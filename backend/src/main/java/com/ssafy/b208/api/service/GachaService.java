package com.ssafy.b208.api.service;


import com.ssafy.b208.api.dto.response.GachaResponseDto;

public interface GachaService {
    GachaResponseDto gacha(String email);

}
