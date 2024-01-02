package com.app.hexarchitecture.capitole.application.port.in;

import com.app.hexarchitecture.capitole.domain.model.entity.Brand;
import com.app.hexarchitecture.capitole.domain.model.entity.Price;
import com.app.hexarchitecture.capitole.domain.model.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceCommand {
    List<Price> execute(Brand brand, Product product, LocalDateTime date);
}
