package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class LegalServiceNotFoundException extends RuntimeException{

    public LegalServiceNotFoundException(String message) {
        super(message);
    }
}
