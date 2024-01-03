package com.app.hexarchitecture.capitole.infrastructure.adapters.in.controller.price.model;

import com.app.hexarchitecture.capitole.domain.model.entity.Price;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CustomPriceResponse(Long productId,
                                  Long brandId,
                                  Integer priceList,
                                  LocalDateTime startDate,
                                  LocalDateTime endDate,
                                  Double amount) {


    public static CustomPriceResponse fromDomain(Price price){
        return CustomPriceResponse.builder()
                .productId(price.product().id())
                .brandId(price.brand().id())
                .priceList(price.priceList())
                .startDate(price.dateRange().startDate())
                .endDate(price.dateRange().endDate())
                .amount(price.currency().amount())
                .build();
    }
}
