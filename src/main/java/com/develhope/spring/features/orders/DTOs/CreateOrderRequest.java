package com.develhope.spring.features.orders.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest { // DTO che rappresenta i dati necessari per creare un nuovo ordine.
    @NotNull
    private BigDecimal caution;
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
