package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class PharmaciesValidationException extends RuntimeException{

    public PharmaciesValidationException(String message) {
        super(message);
    }

    public PharmaciesValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
