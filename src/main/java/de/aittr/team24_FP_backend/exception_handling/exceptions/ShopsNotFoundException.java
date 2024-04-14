package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class ShopsNotFoundException extends RuntimeException{
    public ShopsNotFoundException(String message) {
        super(message);
    }
}
