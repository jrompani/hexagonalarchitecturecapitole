package com.app.hexarchitecture.capitole.infrastructure.adapters.in.controller.price;

import com.app.hexarchitecture.capitole.application.port.in.PriceCommand;
import com.app.hexarchitecture.capitole.domain.model.entity.Brand;
import com.app.hexarchitecture.capitole.domain.model.entity.Product;
import com.app.hexarchitecture.capitole.infrastructure.adapters.in.controller.price.model.CustomPriceResponse;
import com.app.hexarchitecture.capitole.infrastructure.adapters.in.controller.price.model.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController {

    @Autowired
    private PriceCommand priceCommand;

    @GetMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<CustomPriceResponse, Error>>getPricesByDatesAndFinalPrice(@RequestParam @Valid @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime localDateTime,
                                                                              @RequestParam(name="product_id") Long productId,
                                                                              @RequestParam(name="brand_id") Long brandId){

        var result = priceCommand.execute(Brand.builder().id(brandId).build(),
                Product.builder().id(productId).build(),
                localDateTime);

        var response = CustomPriceResponse.fromDomain(result);

        return Response.ok(response);
    }
}
