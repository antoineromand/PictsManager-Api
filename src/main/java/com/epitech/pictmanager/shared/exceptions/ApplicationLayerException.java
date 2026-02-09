package com.epitech.pictmanager.shared.exceptions;

public abstract class ApplicationLayerException extends RuntimeException {
    private String code;

    public ApplicationLayerException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() { return code; }
}
