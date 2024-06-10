package com.inditex.pricefinderapi.application.mapper;

import com.inditex.pricefinderapi.api.dto.PriceDTO;
import com.inditex.pricefinderapi.domain.model.Price;

public class PriceMapper {

    public static PriceDTO toDto(Price price) {
        return PriceDTO.builder()
                       .productId(price.getProductId())
                       .brandId(price.getBrandId())
                       .priceList(price.getPriceList())
                       .startDate(price.getStartDate())
                       .endDate(price.getEndDate())
                       .amount(price.getAmount())
                       .curr(price.getCurr())
                       .build();
    }
}
