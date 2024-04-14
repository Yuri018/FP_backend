package de.aittr.team24_FP_backend.exception_handling;

public class ValidationResponse extends Response{
    private String cause;

    public ValidationResponse(String message, String cause) {
        super(message);
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }
}
