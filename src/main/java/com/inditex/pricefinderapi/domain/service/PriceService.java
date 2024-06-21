package com.inditex.pricefinderapi.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import com.inditex.pricefinderapi.application.dto.PriceDTO;

public interface PriceService {

    List<PriceDTO> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}