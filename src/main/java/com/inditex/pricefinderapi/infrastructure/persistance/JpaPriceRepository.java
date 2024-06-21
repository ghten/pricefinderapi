package com.inditex.pricefinderapi.infrastructure.persistance;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inditex.pricefinderapi.domain.model.Price;
import com.inditex.pricefinderapi.domain.repository.PriceRepository;

public interface JpaPriceRepository extends JpaRepository<Price, Long>, PriceRepository {

    List<Price> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
        Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate);
}
