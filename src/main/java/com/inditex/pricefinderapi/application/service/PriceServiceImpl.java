package com.inditex.pricefinderapi.application.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inditex.pricefinderapi.api.dto.PriceDTO;
import com.inditex.pricefinderapi.application.mapper.PriceMapper;
import com.inditex.pricefinderapi.domain.model.Price;
import com.inditex.pricefinderapi.domain.repository.PriceRepository;
import com.inditex.pricefinderapi.infrastructure.exception.PriceNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public List<PriceDTO> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {

        List<Price> prices = priceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            productId, brandId, applicationDate, applicationDate);

        if (prices.isEmpty()) {
            throw new PriceNotFoundException("No prices found for the given product and brand.");
        }

        return prices.stream()
                     .map(PriceMapper::toDto)
                     .toList();
    }
}
