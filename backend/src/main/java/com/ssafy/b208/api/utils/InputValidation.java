package com.ssafy.b208.api.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class InputValidation {
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
