package com.app.hexarchitecture.capitole.infrastructure.adapters.in.controller.price.model;

import lombok.Builder;

@Builder
public record Error(String code, String error, String cause) {
}
