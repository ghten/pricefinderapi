package com.inditex.pricefinderapi.domain.service;

import java.time.LocalDateTime;

import com.inditex.pricefinderapi.application.dto.PriceDTO;

public interface PriceService {

    PriceDTO getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}