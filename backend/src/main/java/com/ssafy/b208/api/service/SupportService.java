package com.ssafy.b208.api.service;

import com.ssafy.b208.api.dto.MailDto;
import org.springframework.stereotype.Service;

@Service
public interface SupportService {
    public void mailSend(MailDto mailDto, String publicKey);
}
