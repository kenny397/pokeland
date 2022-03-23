package com.ssafy.b208.api.controller;

import com.ssafy.b208.api.service.InitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/init")
@RequiredArgsConstructor
public class InitController {

    private final InitService initService;

    @GetMapping("/mint")
    public void upLoadFile() throws Exception{
        initService.minting();
    }


}
