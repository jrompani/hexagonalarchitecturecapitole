package com.app.hexarchitecture.capitole.domain.model.entity.object;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DateRange(LocalDateTime startDate, LocalDateTime endDate, Integer priceList, Currency currency) {
}
