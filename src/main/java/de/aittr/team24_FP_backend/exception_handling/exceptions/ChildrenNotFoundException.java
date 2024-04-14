package de.aittr.team24_FP_backend.exception_handling.exceptions;

public class ChildrenNotFoundException extends RuntimeException{
    public ChildrenNotFoundException(String message) {
        super(message);
    }
}
