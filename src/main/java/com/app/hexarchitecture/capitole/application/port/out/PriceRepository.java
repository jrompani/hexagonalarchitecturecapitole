package com.app.hexarchitecture.capitole.application.port.out;

import com.app.hexarchitecture.capitole.domain.model.entity.Brand;
import com.app.hexarchitecture.capitole.domain.model.entity.Price;
import com.app.hexarchitecture.capitole.domain.model.entity.Product;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository {
    List<Price> findByBrandIdAndProductIdBetweenDatesParamRange(Brand brand, Product product, LocalDateTime date);
}
