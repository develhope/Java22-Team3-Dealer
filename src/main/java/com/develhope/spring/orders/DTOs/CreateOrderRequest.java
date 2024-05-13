package com.develhope.spring.orders.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest { // DTO che rappresenta i dati necessari per creare un nuovo ordine.
    @NonNull
    private BigDecimal caution;
    @Getter
    @NotNull
    private boolean payed;
    @NotBlank
    private String status;
    @NonNull
    private Long vehicleID;

}
