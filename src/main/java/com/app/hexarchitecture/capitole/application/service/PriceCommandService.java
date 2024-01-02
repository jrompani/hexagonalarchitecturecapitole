package com.app.hexarchitecture.capitole.application.service;

import com.app.hexarchitecture.capitole.application.port.in.PriceCommand;
import com.app.hexarchitecture.capitole.application.port.out.PriceRepository;
import com.app.hexarchitecture.capitole.domain.model.entity.Brand;
import com.app.hexarchitecture.capitole.domain.model.entity.Price;
import com.app.hexarchitecture.capitole.domain.model.entity.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceCommandService implements PriceCommand {

    private final PriceRepository priceRepository;

    public PriceCommandService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> execute(Brand brand, Product product, LocalDateTime date) {
        var test = priceRepository.findByBrandIdAndProductIdBetweenDatesParamRange(brand, product, date);
        System.out.println(test);
        return test;
    }
}
