package com.inditex.pricefinderapi.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.inditex.pricefinderapi.domain.model.Price;

public interface PriceRepository {
    List<Price> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
        Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate);
}