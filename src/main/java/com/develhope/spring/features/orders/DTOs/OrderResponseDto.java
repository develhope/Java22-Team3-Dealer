package com.develhope.spring.features.orders.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private BigDecimal caution;
    private boolean payed;
    private String status;
    private OffsetDateTime orderDate;
    private Long customerId;
    private Long sellerId;
    private Long vehicleId;
}
