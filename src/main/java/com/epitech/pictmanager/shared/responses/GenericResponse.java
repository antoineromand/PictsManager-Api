package com.epitech.pictmanager.shared.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GenericResponse<T> {
    private T data;
    private String message;
    private int httpCode;

    public GenericResponse(String message, int httpCode, T data) {
        this.message = message;
        this.httpCode = httpCode;
        this.data = data;
    }
}
