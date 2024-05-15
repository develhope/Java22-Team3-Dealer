package com.develhope.spring.features.orders.DTOs;

import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVehicleLinkDto {
    private Long id;
    private Long orderId;
    private Long vehicleId;
}
