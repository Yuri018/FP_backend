package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class ShopValidationException extends RuntimeException{

    public ShopValidationException(String message) {
        super(message);
    }

    public ShopValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
