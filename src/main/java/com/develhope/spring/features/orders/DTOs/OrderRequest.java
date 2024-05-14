package com.develhope.spring.features.orders.DTOs;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderRequest { // DTO che rappresenta i dati necessari per creare un nuovo ordine.

    private BigDecimal caution;
    private boolean payed;
    private String status;
    private Long vehicleID;
    private OffsetDateTime orderDate;
}
