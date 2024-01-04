package com.app.hexarchitecture.capitole.tools;

import com.app.hexarchitecture.capitole.domain.model.entity.Brand;
import com.app.hexarchitecture.capitole.domain.model.entity.Price;
import com.app.hexarchitecture.capitole.domain.model.entity.Product;
import com.app.hexarchitecture.capitole.domain.model.entity.object.Currency;
import com.app.hexarchitecture.capitole.domain.model.entity.object.DateRange;

import java.time.LocalDateTime;
import java.util.List;

public class ObjectsConstructor {

    public static Brand buildBrand() {
        return Brand.builder()
                .id(1L)
                .name(null)
                .build();
    }

    public static Product buildProduct() {
        return new Product(55555L, "Co");
    }

    public static Price buildPrice() {
        return Price.builder()
                .id(1L)
                .brand(Brand.builder()
                        .id(1L)
                        .name("ZARA")
                        .build())
                .product(Product.builder()
                        .id(55555L)
                        .build())
                .dateRange(DateRange.builder()
                        .startDate(LocalDateTime.of(2020, 06, 15, 10, 0, 0))
                        .endDate(LocalDateTime.of(2020, 06, 15, 23, 59, 59))
                        .build())
                .priority(2)
                .currency(Currency.builder()
                        .amount(55.0)
                        .isoCode("EUR")
                        .build())
                .build();
    }

    public static List<Price> buildListOfPrices() {
        return List.of(
                Price.builder()
                        .id(1L)
                        .brand(Brand.builder()
                                .id(1L)
                                .name("ZARA")
                                .build())
                        .product(Product.builder()
                                .id(55555L)
                                .build())
                        .dateRange(DateRange.builder()
                                .startDate(LocalDateTime.of(2023, 11, 21, 10, 0, 0))
                                .endDate(LocalDateTime.of(2023, 12, 31, 23, 59, 59))
                                .build())
                        .priority(1)
                        .priceList(1)
                        .currency(Currency.builder()
                                .amount(55.0)
                                .isoCode("EUR")
                                .build())
                        .build(),
                Price.builder()
                        .id(2L)
                        .brand(Brand.builder()
                                .id(1L)
                                .name("ZARA")
                                .build())
                        .product(Product.builder()
                                .id(55555L)
                                .build())
                        .dateRange(DateRange.builder()
                                .startDate(LocalDateTime.of(2023, 12, 15, 10, 0, 0))
                                .endDate(LocalDateTime.of(2024, 1, 31, 23, 59, 59))
                                .build())
                        .priority(2)
                        .priceList(2)
                        .currency(Currency.builder()
                                .amount(50.0)
                                .isoCode("EUR")
                                .build())
                        .build()
        );
    }

}

