package com.inditex.pricefinderapi.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.inditex.pricefinderapi.domain.model.Price;

public interface PriceRepository {
    List<Price> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        Long productId, Long brandId, LocalDateTime date, LocalDateTime dateEnd);
}