package com.harini.oauth.exception;

public class BaseException extends RuntimeException {

    public BaseException(String message){
        super(message);
    }

    public String getErrorCode() {
        return  "";
    }
}
