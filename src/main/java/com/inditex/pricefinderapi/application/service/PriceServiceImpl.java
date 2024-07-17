package com.inditex.pricefinderapi.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inditex.pricefinderapi.application.dto.PriceDTO;
import com.inditex.pricefinderapi.application.mapper.PriceMapper;
import com.inditex.pricefinderapi.domain.repository.PriceRepository;
import com.inditex.pricefinderapi.domain.service.PriceService;
import com.inditex.pricefinderapi.web.exception.PriceNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public PriceDTO getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {

        List<PriceDTO> prices = priceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            productId, brandId, applicationDate, applicationDate)
                                            .stream()
                                            .map(PriceMapper::toDto)
                                            .toList();

        if (prices.isEmpty()) {
            throw new PriceNotFoundException("No prices found for the given product and brand.");
        }

        return prices.get(0);
    }
}
