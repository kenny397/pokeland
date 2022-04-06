package com.ssafy.b208.api.controller;

import com.ssafy.b208.api.dto.response.BaseResponseBody;
import com.ssafy.b208.api.exception.LackMoneyException;
import com.ssafy.b208.api.exception.ExistIdException;
import com.ssafy.b208.api.exception.NonExistentPokedexIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LackMoneyException.class)
    public ResponseEntity<BaseResponseBody> lackMoney(){

        return ResponseEntity.status(400).body(BaseResponseBody.of(400,"돈이 부족합니다."));
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistIdException.class)
    public ResponseEntity<BaseResponseBody> existId(){

        return ResponseEntity.status(400).body(BaseResponseBody.of(400,"존재하는 아이디입니다."));
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NonExistentPokedexIdException.class)
    public ResponseEntity<BaseResponseBody> nonExistPokedexId() {

        return ResponseEntity.status(400).body(BaseResponseBody.of(400, "존재하지 않는 포켓몬 아이디입니다."));
    }
}
