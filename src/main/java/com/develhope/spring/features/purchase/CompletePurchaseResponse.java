package com.develhope.spring.features.purchase;

import com.develhope.spring.features.vehicle.DTOs.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompletePurchaseResponse {
    private Long id;
    private BigDecimal purchaseDeposit;
    private OffsetDateTime purchaseDate;
    private Boolean isPayed;
    private VehicleResponse vehicle;
}

