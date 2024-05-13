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
public class PurchaseRequest {

    @NotNull
    private BigDecimal deposit;

    @Getter
    @NotNull
    private boolean payed;

    @NotNull
    private String status;

    @NotNull
    private Long vehicleID;

    @NotNull
    private OffsetDateTime orderDate;
}
