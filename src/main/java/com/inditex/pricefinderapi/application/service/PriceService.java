package com.inditex.pricefinderapi.application.service;

import java.time.LocalDateTime;
import java.util.List;

import com.inditex.pricefinderapi.api.dto.PriceDTO;

public interface PriceService {

    List<PriceDTO> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}