package com.develhope.spring.features.purchase.DTO;

import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponse {
    private Long id;
    private BigDecimal deposit;
    private boolean payed;
    private String status;
    private VehicleEntity vehicleId;
    private OffsetDateTime purchaseDate;
}
