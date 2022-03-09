package com.harini.oauth.exception;


public class AuthenticationException extends BaseException {


    public AuthenticationException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "1005";
    }
}
