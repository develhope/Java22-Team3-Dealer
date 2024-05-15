package com.develhope.spring.features.orders.model;

import com.develhope.spring.features.orders.DTOs.OrderVehicleLinkDto;
import com.develhope.spring.features.orders.DTOs.OrderVehicleLinkResponse;
import com.develhope.spring.features.orders.entity.OrderEntity;
import com.develhope.spring.features.orders.entity.OrderVehicleLinkEntity;
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

    public static OrderVehicleLinkModel entityToModel (OrderVehicleLinkEntity entity) {
        return new OrderVehicleLinkModel(entity.getId(), entity.getOrderEntity().getId(), entity.getVehicleEntity().getVehicleId());
    }

    public static OrderVehicleLinkEntity modelToEntity (OrderVehicleLinkModel model) {
        OrderVehicleLinkEntity entity = new OrderVehicleLinkEntity();
        entity.setId(model.getId());
        entity.setOrderEntity(new OrderEntity(model.getOrderId()));
        entity.setVehicleEntity(new VehicleEntity(model.getVehicleId()));
        return entity;
    }

    public static OrderVehicleLinkModel dtoToModel (OrderVehicleLinkDto dto) {
        return new OrderVehicleLinkModel(dto.getId(), dto.getOrderId(), dto.getVehicleId());
    }

    public static OrderVehicleLinkDto modelToDto (OrderVehicleLinkModel model) {
        OrderVehicleLinkDto dto = new OrderVehicleLinkDto();
        dto.setId(model.getId());
        dto.setOrderId(model.getOrderId());
        dto.setVehicleId(model.getVehicleId());
        return dto;
    }

    public static OrderVehicleLinkResponse modelToResponse (OrderVehicleLinkModel model, VehicleEntity vehicleEntity) {
        OrderVehicleLinkResponse response = new OrderVehicleLinkResponse();
        response.setId(model.getId());
        response.setOrderId(model.getOrderId());
        response.setVehicleEntity(vehicleEntity);
        return response;
    }
}
