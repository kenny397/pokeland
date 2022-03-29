package com.ssafy.b208.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDto {
    String category;
    String address;
    String title;
    String message;
}
