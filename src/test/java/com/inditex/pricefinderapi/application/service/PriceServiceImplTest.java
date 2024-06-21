package com.inditex.pricefinderapi.application.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.inditex.pricefinderapi.application.dto.PriceDTO;
import com.inditex.pricefinderapi.domain.model.Price;
import com.inditex.pricefinderapi.domain.repository.PriceRepository;
import com.inditex.pricefinderapi.infrastructure.exception.PriceNotFoundException;

class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return prices when prices are available")
    void shouldReturnPricesWhenPricesAreAvailable() {
        when(priceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(any(), any(), any(), any()))
            .thenReturn(Collections.singletonList(new Price()));

        List<PriceDTO> prices = priceService.getApplicablePrice(1L, 1L, LocalDateTime.now());

        assertFalse(prices.isEmpty());
    }

    @Test
    @DisplayName("Should throw PriceNotFoundException when no prices are available")
    void shouldThrowPriceNotFoundExceptionWhenNoPricesAreAvailable() {
        when(priceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(any(), any(), any(), any()))
            .thenReturn(Collections.emptyList());

        assertThrows(PriceNotFoundException.class, () -> priceService.getApplicablePrice(1L, 1L, LocalDateTime.now()));
    }
}
