package com.inditex.pricefinderapi.api.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PriceDTO {

    private Long          brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long          priceList;
    private Long          productId;
    private int           priority;
    private double        amount;
    private String        curr;
}
