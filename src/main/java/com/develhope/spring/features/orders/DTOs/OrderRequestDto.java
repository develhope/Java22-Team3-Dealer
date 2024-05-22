package com.develhope.spring.features.orders.DTOs;

import com.develhope.spring.features.orders.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    private BigDecimal caution;
    private boolean payed;
    private OrderStatus status;
    private OffsetDateTime orderDate;
    private Long customerId;
    private Long sellerId;
    private Long vehicleId;
}
