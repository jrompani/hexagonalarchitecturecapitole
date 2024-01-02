package com.app.hexarchitecture.capitole.infrastructure.adapters.out.persistence.price.repository;

import com.app.hexarchitecture.capitole.application.port.out.PriceRepository;
import com.app.hexarchitecture.capitole.domain.model.entity.Brand;
import com.app.hexarchitecture.capitole.domain.model.entity.Price;
import com.app.hexarchitecture.capitole.domain.model.entity.Product;
import com.app.hexarchitecture.capitole.infrastructure.adapters.out.persistence.price.mapper.PriceMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PriceH2Repository implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    public PriceH2Repository(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    @Override
    public List<Price> findByBrandIdAndProductIdBetweenDatesParamRange(Brand brand, Product product, LocalDateTime date) {
        return priceJpaRepository.findByBrandIdAndProductIdBetweenDatesParamRange(brand.id(), product.id(), date)
                .stream()
                .map(PriceMapper::toDomain)
                .toList();
    }
}
