package com.inditex.pricefinderapi.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.inditex.pricefinderapi.application.dto.PriceDTO;
import com.inditex.pricefinderapi.domain.model.Price;
import com.inditex.pricefinderapi.domain.repository.PriceRepository;
import com.inditex.pricefinderapi.web.exception.PriceNotFoundException;

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
        Price priceEntity = new Price();
        priceEntity.setBrandId(1L);
        priceEntity.setProductId(35455L);
        priceEntity.setPriceList(1L);
        priceEntity.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        priceEntity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
        priceEntity.setAmount(35.50);
        priceEntity.setPriority(0);

        when(priceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(any(), any(), any(), any()))
            .thenReturn(Collections.singletonList(priceEntity));

        PriceDTO price = priceService.getApplicablePrice(1L, 1L, LocalDateTime.now());

        assertNotNull(price);
        assertEquals(35.50, price.getAmount());
    }

    @Test
    @DisplayName("Should throw PriceNotFoundException when no prices are available")
    void shouldThrowPriceNotFoundExceptionWhenNoPricesAreAvailable() {
        when(priceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(any(), any(), any(), any()))
            .thenReturn(Collections.emptyList());

        assertThrows(PriceNotFoundException.class, () -> priceService.getApplicablePrice(1L, 1L, LocalDateTime.now()));
    }
}
