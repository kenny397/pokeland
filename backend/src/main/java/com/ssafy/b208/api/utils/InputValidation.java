package com.ssafy.b208.api.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class InputValidation {

    public static boolean patternMatches(String validationData, String regexPattern) {
        if (validationData == null || regexPattern == null)
            return false;
        return Pattern.compile(regexPattern)
                .matcher(validationData)
                .matches();
    }
}
