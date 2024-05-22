package com.develhope.spring.features.purchase.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestDTO {
    private BigDecimal purchaseDeposit;
    private Boolean isPayed;
    private Long vehicleID;
    private OffsetDateTime orderDate;
}
