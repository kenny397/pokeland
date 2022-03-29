package com.ssafy.b208.api.controller;

import com.ssafy.b208.api.dto.MailDto;
import com.ssafy.b208.api.service.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/support")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class SupportController {

    @Autowired
    private final SupportService supportService;

    @PostMapping("/")
    public void sendMail(@RequestBody MailDto mailDto) {
        supportService.mailSend(mailDto);
    }
}
