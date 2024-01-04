package com.app.hexarchitecture.capitole.application.exceptions;

public class ResourceNotAvailableException extends RuntimeException{
    public static final String ERROR_CODE = "resourceNotAvailable";

    public ResourceNotAvailableException(String message) {
        super(message);
    }
}
