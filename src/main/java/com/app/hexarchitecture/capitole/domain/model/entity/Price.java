package com.app.hexarchitecture.capitole.domain.model.entity;

import com.app.hexarchitecture.capitole.domain.model.entity.object.Currency;
import com.app.hexarchitecture.capitole.domain.model.entity.object.DateRange;
import lombok.Builder;

@Builder
public record Price(Long id, Brand brand, DateRange dateRange, Integer priceList, Product product, Integer priority, Currency currency) {
}
