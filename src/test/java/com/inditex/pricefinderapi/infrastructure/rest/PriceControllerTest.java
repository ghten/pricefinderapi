package com.inditex.pricefinderapi.infrastructure.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.inditex.pricefinderapi.application.dto.PriceDTO;
import com.inditex.pricefinderapi.domain.service.PriceService;

class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
    }


    @Test
    @DisplayName("Should return 200 when valid request")
    void shouldReturn200WhenValidRequest() throws Exception {
        PriceDTO priceDTO = PriceDTO.builder()
                                    .brandId(1L)
                                    .productId(1L)
                                    .priceList(1L)
                                    .amount(1.0)
                                    .curr("EUR")
                                    .startDate(LocalDateTime.now())
                                    .endDate(LocalDateTime.now())
                                    .priority(1)
                                    .build();

        when(priceService.getApplicablePrice(any(), any(), any())).thenReturn(Collections.singletonList(priceDTO));

        mockMvc.perform(get("/prices")
                            .param("productId", "1")
                            .param("brandId", "1")
                            .param("applicationDate", LocalDateTime.now().toString())
                            .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return 400 when missing parameters")
    void shouldReturn400WhenMissingParameters() throws Exception {
        mockMvc.perform(get("/prices")
                            .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return 500 when service throws exception")
    void shouldReturn500WhenServiceThrowsException() throws Exception {
        when(priceService.getApplicablePrice(any(), any(), any())).thenThrow(new RuntimeException());

        mockMvc.perform(get("/prices")
                            .param("productId", "1")
                            .param("brandId", "1")
                            .param("applicationDate", LocalDateTime.now().toString())
                            .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isInternalServerError());
    }
}
