package com.app.hexarchitecture.capitole.infrastructure.adapters.out.persistence.price.repository;

import com.app.hexarchitecture.capitole.infrastructure.adapters.out.persistence.price.entities.EntityPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceJpaRepository extends JpaRepository<EntityPrice, Long> {
    @Query("SELECT e FROM EntityPrice e " +
            "WHERE :brandId = e.brand.id " +
            "AND :productId = e.product.id " +
            "AND (:dateParam BETWEEN e.startDate AND e.endDate) ")
    List<EntityPrice> findByBrandIdAndProductIdBetweenDatesParamRange(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("dateParam") LocalDateTime dateParam);
}
