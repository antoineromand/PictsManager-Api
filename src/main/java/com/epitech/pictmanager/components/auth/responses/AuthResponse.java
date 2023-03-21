package com.epitech.pictmanager.components.auth.responses;

public class AuthResponse {
    private String message;
    private int httpCode;

    public AuthResponse(String message, int httpCode) {
        this.message = message;
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
