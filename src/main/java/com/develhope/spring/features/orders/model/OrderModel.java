package com.develhope.spring.features.orders.model;

import com.develhope.spring.features.orders.DTOs.OrderRequestDto;
import com.develhope.spring.features.orders.DTOs.OrderResponseDto;
import com.develhope.spring.features.orders.entity.OrderEntity;
import com.develhope.spring.features.orders.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    private Long id;
    private BigDecimal caution;
    private boolean payed;
    private OrderStatus status;
    private OffsetDateTime orderDate;
    private Long customerId;
    private Long sellerId;
    private Long vehicleId;

    public static OrderModel entityToModel(OrderEntity entity) {
        return new OrderModel(
                entity.getId(),
                entity.getCaution(),
                entity.isPayed(),
                entity.getStatus(),
                entity.getOrderDate(),
                entity.getCustomerId(),
                entity.getSellerId(),
                entity.getVehicleId()
        );
    }

    public static OrderEntity modelToEntity(OrderModel model) {
        return new OrderEntity(
                model.getId(),
                model.getCaution(),
                model.isPayed(),
                model.getStatus(),
                model.getOrderDate(),
                model.getCustomerId(),
                model.getSellerId(),
                model.getVehicleId()
        );
    }

    public static OrderModel dtoToModel(OrderRequestDto dto) {
        return new OrderModel(
                null,
                dto.getCaution(),
                dto.isPayed(),
                dto.getStatus(),
                dto.getOrderDate(),
                dto.getCustomerId(),
                dto.getSellerId(),
                dto.getVehicleId()
        );
    }

    public static OrderResponseDto modelToDto(OrderModel model) {
        return new OrderResponseDto(
                model.getId(),
                model.getCaution(),
                model.isPayed(),
                model.getStatus().toString(),
                model.getOrderDate(),
                model.getCustomerId(),
                model.getSellerId(),
                model.getVehicleId()
        );
    }

    public static List<OrderModel> entityListToModelList(List<OrderEntity> entities) {
        return entities.stream()
                .map(OrderModel::entityToModel)
                .collect(Collectors.toList());
    }
}
