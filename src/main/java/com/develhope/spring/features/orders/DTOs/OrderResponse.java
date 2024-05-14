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
public class OrderResponse { //DTO utilizzato per rappresentare la risposta di un'operazione di visualizzazione di un ordine.
    private Long id;
    private BigDecimal caution;
    private boolean payed;
    private String status;
    private VehicleEntity vehicleEntityId;
    private OffsetDateTime orderDate;

}