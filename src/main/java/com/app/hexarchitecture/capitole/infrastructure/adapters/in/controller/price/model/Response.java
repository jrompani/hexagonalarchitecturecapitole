package com.app.hexarchitecture.capitole.infrastructure.adapters.in.controller.price.model;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;

@Builder
public record Response<T,E>(LocalDateTime timeStamp, T data, E error, boolean success) {

    public static <T, E> ResponseEntity<Response<T, E>> ok(T data) {
        return ResponseEntity.ok(Response.<T, E>builder().success(true).data(data).build());
    }

    public static <T, E> ResponseEntity<Response<T, E>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(Response.<T, E>builder().success(true).data(data).build());
    }

    public static <T, E> ResponseEntity<Response<T, E>> error(E error, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(Response.<T, E>builder().success(false).error(error).build());
    }

    public static <T, E> Response<T, E> error(E error) {
        return Response.<T, E>builder()
                .success(false)
                .data(null)
                .error(error)
                .build();
    }

        public ResponseEntity<Response<T, E>> toResponseEntity(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }

}
