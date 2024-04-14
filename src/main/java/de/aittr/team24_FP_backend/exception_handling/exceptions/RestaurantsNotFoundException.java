package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class RestaurantsNotFoundException extends RuntimeException{
    public RestaurantsNotFoundException(String message) {
        super(message);
    }
}
