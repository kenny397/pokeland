package com.ssafy.b208.api.service.utils;

import java.util.regex.Pattern;

public class InputValidation {
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
