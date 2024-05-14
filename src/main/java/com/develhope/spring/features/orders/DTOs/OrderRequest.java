package com.develhope.spring.features.orders.DTOs;

import com.develhope.spring.features.orders.entity.OrderStatus;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderRequest { // DTO che rappresenta i dati necessari per creare un nuovo ordine.

    private Long id;
    private BigDecimal caution;
    private boolean payed;
    private OrderStatus status;
    private VehicleEntity vehicle;
    private OffsetDateTime orderDate;
}
