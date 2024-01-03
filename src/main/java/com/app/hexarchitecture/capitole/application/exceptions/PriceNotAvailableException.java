package com.app.hexarchitecture.capitole.application.exceptions;

public class PriceNotAvailableException extends RuntimeException{
    public PriceNotAvailableException() {
        super("Price not available for the specified parameters.");
    }
}
