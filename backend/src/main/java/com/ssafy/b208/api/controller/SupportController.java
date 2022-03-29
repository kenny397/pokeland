package com.ssafy.b208.api.controller;

import com.ssafy.b208.api.dto.MailDto;
import com.ssafy.b208.api.service.SupportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "고객센터 API", tags = {"support-controller"})
@RestController
@RequestMapping("/api/v1/support")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class SupportController {

    @Autowired
    private final SupportService supportService;

    @PostMapping("/")
    @ApiOperation(value = "고객센터 글 쓰기", notes = "고객센터에 글을 작성하고 그 내용을 관리자의 메일주소로 발송한다.")
    public void sendMail(@RequestBody MailDto mailDto) {
        supportService.mailSend(mailDto);
    }
}