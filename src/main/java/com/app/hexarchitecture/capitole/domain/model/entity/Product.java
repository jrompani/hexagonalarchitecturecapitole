package com.app.hexarchitecture.capitole.domain.model.entity;

import lombok.Builder;

@Builder
public record Product(Long id, String name) {
}
