package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class PharmaciesNotFoundException extends RuntimeException{

    public PharmaciesNotFoundException(String message) {
        super(message);
    }
}
