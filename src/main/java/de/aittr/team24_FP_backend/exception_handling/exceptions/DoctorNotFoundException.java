package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class DoctorNotFoundException extends RuntimeException{

    public DoctorNotFoundException(String message) {
        super(message);
    }
}
