package com.develhope.spring.features.orders.model;

import com.develhope.spring.features.orders.DTOs.OrderVehicleLinkResponse;
import com.develhope.spring.features.orders.entity.OrderEntity;
import com.develhope.spring.features.orders.entity.LinkOrderUserVehicleEntity;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderVehicleLinkModel {
    private Long id;
    private Long orderId;
    private Long vehicleId;

    public static OrderVehicleLinkModel entityToModel (LinkOrderUserVehicleEntity entity) {
        return new OrderVehicleLinkModel(entity.getId(), entity.getOrderEntity().getId(), entity.getVehicleEntity().getVehicleId());
    }


    }


