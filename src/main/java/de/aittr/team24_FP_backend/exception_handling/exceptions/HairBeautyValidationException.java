package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class HairBeautyValidationException extends RuntimeException{

    public HairBeautyValidationException(String message) {
        super(message);
    }

    public HairBeautyValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
