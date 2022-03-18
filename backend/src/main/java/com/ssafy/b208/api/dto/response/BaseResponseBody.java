package com.ssafy.b208.api.dto.response;


import lombok.Getter;

@Getter
public class BaseResponseBody {
    String message= null;
    Integer statusCode = null;

    public BaseResponseBody() {}

    public static BaseResponseBody of(Integer statusCode, String message) {
        BaseResponseBody body = new BaseResponseBody();
        body.message = message;
        body.statusCode = statusCode;
        return body;
    }
}
