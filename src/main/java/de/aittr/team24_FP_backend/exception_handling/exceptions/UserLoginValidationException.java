package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class UserLoginValidationException extends RuntimeException{
    public UserLoginValidationException(String message) {
        super(message);
    }

    public UserLoginValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
