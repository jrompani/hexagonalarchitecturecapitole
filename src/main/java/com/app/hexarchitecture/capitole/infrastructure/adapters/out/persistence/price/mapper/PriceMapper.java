package com.app.hexarchitecture.capitole.infrastructure.adapters.out.persistence.price.mapper;

import com.app.hexarchitecture.capitole.domain.model.entity.Brand;
import com.app.hexarchitecture.capitole.domain.model.entity.Price;
import com.app.hexarchitecture.capitole.domain.model.entity.Product;
import com.app.hexarchitecture.capitole.domain.model.entity.object.Currency;
import com.app.hexarchitecture.capitole.domain.model.entity.object.DateRange;
import com.app.hexarchitecture.capitole.infrastructure.adapters.out.persistence.price.entities.EntityPrice;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PriceMapper {

    public static Price toDomain(EntityPrice entityPrice){
        return Price.builder()
                .id(entityPrice.getId())
                .brand(Brand.builder()
                        .id(entityPrice.getBrand().getId())
                        .name(entityPrice.getBrand().getName()).build())
                .product(Product.builder()
                        .id(entityPrice.getProduct().getId()).build())
                .dateRange(DateRange.builder()
                        .startDate(entityPrice.getStartDate())
                        .endDate(entityPrice.getEndDate()).build())
                .priority(entityPrice.getPriority())
                .currency(Currency.builder()
                        .isoCode(entityPrice.getCurrency())
                        .amount(entityPrice.getPrice()).build())
                .priceList(entityPrice.getPriceList())
                .build();
    }
}
