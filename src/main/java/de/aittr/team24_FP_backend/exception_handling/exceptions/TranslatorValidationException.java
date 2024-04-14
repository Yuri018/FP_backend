package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class TranslatorValidationException extends RuntimeException{

    public TranslatorValidationException(String message) {
        super(message);
    }

    public TranslatorValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
