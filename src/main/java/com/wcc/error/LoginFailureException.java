package com.wcc.error;

public class LoginFailureException extends RuntimeException {
    public LoginFailureException() {
        super("username or password invalid.");
    }

    public LoginFailureException(String message) {
        super(message);
    }
}
