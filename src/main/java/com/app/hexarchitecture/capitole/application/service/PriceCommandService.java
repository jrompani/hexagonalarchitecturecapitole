package com.app.hexarchitecture.capitole.application.service;

import com.app.hexarchitecture.capitole.application.exceptions.PriceNotAvailableException;
import com.app.hexarchitecture.capitole.application.port.in.PriceCommand;
import com.app.hexarchitecture.capitole.application.port.out.PriceRepository;
import com.app.hexarchitecture.capitole.domain.model.entity.Brand;
import com.app.hexarchitecture.capitole.domain.model.entity.Price;
import com.app.hexarchitecture.capitole.domain.model.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;

@Service
@Slf4j
public class PriceCommandService implements PriceCommand {

    @Autowired
    private final PriceRepository priceRepository;

    public PriceCommandService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price execute(Brand brand, Product product, LocalDateTime date) {
        return priceRepository.findByBrandIdAndProductIdBetweenDatesParamRange(brand, product, date)
                .stream()
                .max(Comparator.comparingInt(Price::priority))
                .orElseThrow(() -> {
                    log.error("Price not available for {}, {}, {}", brand.id(), product.id(), date);
                    return new PriceNotAvailableException();
                });
    }
}
