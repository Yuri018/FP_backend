package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class UserLoginNotFoundException extends RuntimeException{
    public UserLoginNotFoundException(String message) {
        super(message);
    }
}
