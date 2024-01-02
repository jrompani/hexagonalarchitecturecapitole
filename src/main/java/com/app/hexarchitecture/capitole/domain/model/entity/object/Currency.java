package com.app.hexarchitecture.capitole.domain.model.entity.object;

import lombok.Builder;

@Builder
public record Currency(String isoCode, Double amount) {
}
