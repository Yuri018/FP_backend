package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class RestaurantValidationException extends RuntimeException{
    public RestaurantValidationException(String message) {
        super(message);
    }

    public RestaurantValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
