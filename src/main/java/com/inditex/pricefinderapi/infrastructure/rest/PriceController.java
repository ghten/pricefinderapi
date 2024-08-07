package com.inditex.pricefinderapi.infrastructure.rest;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.pricefinderapi.application.dto.ErrorDTO;
import com.inditex.pricefinderapi.application.dto.PriceDTO;
import com.inditex.pricefinderapi.domain.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Price Api")
public class PriceController {

    private final PriceService priceService;

    @Operation(
        operationId = "getPrices",
        description = "Returns prices by productId, BrandId and applicationDate")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", useReturnTypeSchema = true
        ),
        @ApiResponse(responseCode = "404", description = "Not Found", content = {
            @Content(schema = @Schema(implementation = ErrorDTO.class))
        }),
        @ApiResponse(responseCode = "500", description = "Failure", content = {
            @Content(schema = @Schema(implementation = ErrorDTO.class))
        })
    })
    @GetMapping("/prices")
    public ResponseEntity<PriceDTO> getPrices(
        @Parameter(description = "ID product", required = true) @RequestParam Long productId,
        @Parameter(description = "ID brand", required = true) @RequestParam Long brandId,
        @Parameter(description = "Format date: yyyy-mm-ddT00:00:00", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {

       return ResponseEntity.ok(priceService.getApplicablePrice(productId, brandId, applicationDate));

    }
}
