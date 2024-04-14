package de.aittr.team24_FP_backend.exception_handling;

public class Response {

    protected String message;

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
