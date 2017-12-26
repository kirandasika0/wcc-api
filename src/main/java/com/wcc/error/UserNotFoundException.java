package com.wcc.error;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String localizedErrorMessage) {
        super(localizedErrorMessage);
    }
}
