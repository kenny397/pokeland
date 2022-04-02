package com.ssafy.b208.api.exception;

public class LackMoneyException extends RuntimeException{
    public LackMoneyException(Long id){
        super("돈이 부족합니다."+ id);
    }
}
