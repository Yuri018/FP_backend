package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(String message) {
        super(message);
    }
}
