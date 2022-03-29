package com.ssafy.b208.api.service;

import com.ssafy.b208.api.dto.MailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {
    @Autowired
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "YOUR_EMAIL_ADDRESS";

    @Override
    public void mailSend(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        String title = "[" + mailDto.getCategory() + "] " + mailDto.getTitle();
        message.setTo(mailDto.getAddress());
        message.setFrom(SupportServiceImpl.FROM_ADDRESS);
        message.setSubject(title);
        message.setText(mailDto.getMessage());
        mailSender.send(message);
    }
}
