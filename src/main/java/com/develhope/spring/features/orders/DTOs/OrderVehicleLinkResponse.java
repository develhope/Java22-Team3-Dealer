package com.develhope.spring.features.orders.DTOs;

import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVehicleLinkResponse {
    private Long id;
    private Long OrderId;
    private BigDecimal caution;
    private boolean payed;
    private String status;
    private VehicleEntity vehicleEntity;
    private OffsetDateTime orderDate;
}
