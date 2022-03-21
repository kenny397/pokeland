package com.ssafy.b208.api.service;

import com.ssafy.b208.api.dto.request.WalletRequestDto;
import org.springframework.stereotype.Service;

import java.io.IOException;


public interface GachaService {
    public void getNft(WalletRequestDto wallet) throws Exception;

}
