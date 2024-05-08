package com.develhope.spring.orders.DTOs;

import com.develhope.spring.orders.entity.Order;
import com.develhope.spring.orders.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private BigDecimal caution;
    private boolean payed;
    private String status;
    private Long vehicleId;

}