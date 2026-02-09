package com.epitech.pictmanager.shared.responses;

import java.util.ArrayList;
import java.util.List;

public class GenericUpdateResponse {
    private String message;
    private List<String> updatedFields;
    private int httpCode;

    public GenericUpdateResponse(String message, int httpCode, List<String> updatedFields) {
        this.message = message;
        this.httpCode = httpCode;
        this.updatedFields = updatedFields;
    }

    public String getMessage() {
        return message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public List<String> getUpdatedFields() {
        return updatedFields;
    }
}
