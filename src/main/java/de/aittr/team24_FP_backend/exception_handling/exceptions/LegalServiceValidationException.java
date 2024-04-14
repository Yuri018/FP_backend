package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class LegalServiceValidationException extends RuntimeException{

    public LegalServiceValidationException(String message) {
        super(message);
    }

    public LegalServiceValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
