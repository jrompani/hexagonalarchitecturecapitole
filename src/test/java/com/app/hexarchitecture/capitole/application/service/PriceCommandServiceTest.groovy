package com.app.hexarchitecture.capitole.application.service

import com.app.hexarchitecture.capitole.application.port.out.PriceRepository
import com.app.hexarchitecture.capitole.domain.model.entity.Brand
import com.app.hexarchitecture.capitole.domain.model.entity.Price
import com.app.hexarchitecture.capitole.domain.model.entity.Product
import com.app.hexarchitecture.capitole.domain.model.entity.object.Currency
import com.app.hexarchitecture.capitole.domain.model.entity.object.DateRange
import com.app.hexarchitecture.capitole.infrastructure.adapters.in.controller.price.model.Response
import com.app.hexarchitecture.capitole.infrastructure.exception.GlobalExceptionHandler
import spock.lang.Specification
import org.slf4j.Logger;
import spock.lang.Subject
import com.app.hexarchitecture.capitole.application.exceptions.*

import java.time.LocalDateTime
import com.app.hexarchitecture.capitole.tools.*

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

class PriceCommandServiceTest extends Specification {

    @Subject
    private PriceCommandService priceCommandService

    private logger = Mock(Logger)

    private ObjectsConstructor objectsConstructor = Mock(ObjectsConstructor);

    private PriceRepository priceRepository = Mock(PriceRepository);

    def setup() {
        priceCommandService = new PriceCommandService(priceRepository)
    }

    def "execute should return price with higher priority"() {
        given:
        def brand = objectsConstructor.buildBrand()
        def product = objectsConstructor.buildProduct()
        def date = LocalDateTime.of(2022, 1, 1, 12, 0, 0)
        def resultExpected = objectsConstructor.buildPrice()

        def listPrices = objectsConstructor.buildListOfPrices()

        priceRepository.findByBrandIdAndProductIdBetweenDatesParamRange(brand, product, date) >> listPrices

        when:
        def result = priceCommandService.execute(brand, product, date)

        then:
        result.priority() == resultExpected.priority()
        result.currency().isoCode() == resultExpected.currency().isoCode()
    }

    def "execute should throw priceNotAvailableException if prices response is empty"() {
        given:
        def brand = objectsConstructor.buildBrand()
        def product = objectsConstructor.buildProduct()
        def date = LocalDateTime.of(2022, 1, 1, 12, 0, 0)
        def resultExpected = objectsConstructor.buildPrice()

        priceRepository.findByBrandIdAndProductIdBetweenDatesParamRange(brand, product, date) >> []

        when:
        priceCommandService.execute(brand, product, date)

        then:
        thrown(PriceNotAvailableException)
    }

    public static Price buildSamplePrice() {
        return Price.builder()
                .id(1L)
                .brand(new Brand(1L, "ZARA"))
                .dateRange(new DateRange(
                        LocalDateTime.parse("2020-06-15T16:00"),
                        LocalDateTime.parse("2020-12-31T23:59:59"),
                        null,
                        null
                ))
                .priceList(1)
                .product(new Product(35455L, "Cotton Shirt"))
                .priority(1)
                .currency(Currency.builder().isoCode("EUR").amount(38.95).build())
                .build();
    }

    public static Brand buildSampleBrand(){
        return Brand.builder()
                .id(1L)
                .name("ZARA").build();
    }

    public static Product buildSampleProduct(){
        return Product.builder().id(35455L).name("Cotton Shirt").build();
    }

}