package com.inditex.pricefinderapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorDTO {
    private String message;
    private int statusCode;
}
