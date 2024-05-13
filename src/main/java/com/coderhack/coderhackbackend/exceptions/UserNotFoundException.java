package com.coderhack.coderhackbackend.exceptions;

public class UserNotFoundException extends Exception {
    public static final String ERROR_CODE = "USR-404";
    public static final String ERROR_MESSAGE = ERROR_CODE + " Sorry, requested user not found";

    public UserNotFoundException(){
        super(ERROR_MESSAGE);
    }
    
}
