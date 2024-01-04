package com.app.hexarchitecture.capitole.infrastructure.exception;

import com.app.hexarchitecture.capitole.application.exceptions.ResourceNotAvailableException;
import com.app.hexarchitecture.capitole.infrastructure.adapters.in.controller.price.model.Response;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.app.hexarchitecture.capitole.infrastructure.adapters.in.controller.price.model.Error;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger;

    public GlobalExceptionHandler(Logger logger) {
        this.logger = logger;
    }

    @ExceptionHandler(value = {ResourceNotAvailableException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response<Object, Error> handlePriceNotFoundException(final ResourceNotAvailableException ex) {
        return Response.error(
                Error.builder()
                        .code(ResourceNotAvailableException.ERROR_CODE)
                        .error(ex.getClass().getSimpleName())
                        .cause("Incorrect parameters or Entity is not available")
                        .build());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Object, Error> handleUncaughtException(final Exception e) {
        logger.error("Uncaught exception", e);
        return Response.error(
                Error.builder()
                        .code("uncaughtException")
                        .error(e.getClass().getSimpleName())
                        .cause(e.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<Object, Error> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        var errorMessage = String.format("Invalid argument type: %s should be %s",
                ex.getName(),
                Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
        return Response.error(
                Error.builder()
                        .code(ex.getErrorCode())
                        .error(ex.getClass().getSimpleName())
                        .cause(errorMessage)
                        .build());

    }


}
