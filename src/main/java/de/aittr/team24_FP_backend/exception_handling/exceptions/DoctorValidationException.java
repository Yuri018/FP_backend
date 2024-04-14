package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class DoctorValidationException extends RuntimeException{

    public DoctorValidationException(String message) {
        super(message);
    }

    public DoctorValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
