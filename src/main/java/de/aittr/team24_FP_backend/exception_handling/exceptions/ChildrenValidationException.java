package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class ChildrenValidationException extends RuntimeException{
    public ChildrenValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChildrenValidationException(String message) {
        super(message);
    }
}
