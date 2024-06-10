package com.inditex.pricefinderapi.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long          id;
    private Long          brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long          priceList;
    private Long          productId;
    private int           priority;
    @Column(name = "price")
    private double amount;
    private String        curr;
}
