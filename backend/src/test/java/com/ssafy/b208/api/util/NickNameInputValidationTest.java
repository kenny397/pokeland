package com.ssafy.b208.api.util;

import com.ssafy.b208.api.utils.InputValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NickNameInputValidationTest {
    private final static String nickNameRegexPattern = "^(?=.{2,10}$)[^-@!$%^&*()_+|~=`\\\\#{}\\[\\]:\";'<>?,.\\/\\s]+$";
    private final static char[] specialArr = {'@', '-', '!', '$', '%', '^', '&', '*', '(', ')', '-', '+', '|', '=', '`', '\\', '#', '{', '}', '[', ']', ':', '"', ';', '\'', '<', '>', '?', ',', '.', '/'};

    @Test
    public void validatePassword_NullString() {
        // setup
        String nickName = "aa";
        // execute
        boolean actual = InputValidation.patternMatches(nickName, nickNameRegexPattern);
        // assert
        assertTrue(actual);
    }

    @Test
    public void validatePassword_EmptyString() {
        // setup
        String nickName = "aaaaandreaa";
        // execute
        boolean actual = InputValidation.patternMatches(nickName, nickNameRegexPattern);
        // assert
        assertFalse(actual);
    }

    @Test
    public void validatePassword_Missing_OneNumber() {
        // setup
        String nickName = "a";
        // execute
        boolean actual = InputValidation.patternMatches(nickName, nickNameRegexPattern);
        // assert
        assertFalse(actual);
    }

    @Test
    public void validatePassword_Missing_OneUpperCaseLetter() {
        // setup
        String nickName = "aaaaandrea";
        // execute
        boolean actual = InputValidation.patternMatches(nickName, nickNameRegexPattern);
        // assert
        assertTrue(actual);

    }

    @Test
    public void validatePassword_Missing_OneLowerCaseLetter2() {
        // setup
        String nickName = "test";
        StringBuffer sb = new StringBuffer();
        sb.append(nickName);
        for (int j = 0; j < nickName.length(); j++) {
            for (int i = 0; i < specialArr.length; i++) {
                sb = new StringBuffer();
                sb.append(nickName);
                String testData = String.valueOf(sb.insert(j,specialArr[i]));
                System.out.println(testData);
                boolean actual = InputValidation.patternMatches(testData, nickNameRegexPattern);
                assertFalse(actual);
            }
        }
    }

    @Test
    public void validatePassword_Missing_OneLowerCaseLetter3() {
        // setup
        String nickName = " test";
        System.out.println(nickName);
        boolean actual = InputValidation.patternMatches(nickName, nickNameRegexPattern);
        assertFalse(actual);
        actual = InputValidation.patternMatches("te st", nickNameRegexPattern);
        assertFalse(actual);
        actual = InputValidation.patternMatches("tes t", nickNameRegexPattern);
        assertFalse(actual);
        actual = InputValidation.patternMatches("test ", nickNameRegexPattern);
        assertFalse(actual);

    }

}
