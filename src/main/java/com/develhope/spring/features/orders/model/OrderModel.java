package com.develhope.spring.features.orders.model;

import com.develhope.spring.features.orders.DTOs.OrderRequest;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import com.develhope.spring.features.orders.DTOs.OrderResponse;
import com.develhope.spring.features.orders.entity.OrderEntity;
import com.develhope.spring.features.orders.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    private Long orderId;
    private BigDecimal caution;
    private boolean payed;
    private OrderStatus status;
    private VehicleEntity vehicleEntity;
    private OffsetDateTime orderDate;

    public OrderModel(BigDecimal caution, boolean payed, OrderStatus status, VehicleEntity vehicleEntity, OffsetDateTime orderDate) {
        this.caution = caution;
        this.payed = payed;
        this.status = status;
        this.vehicleEntity = vehicleEntity;
        this.orderDate = orderDate;
    }



    public static OrderEntity dtoToEntity (OrderRequest request) { // Converte un oggetto OrderRequest in un oggetto OrderEntity
        return new OrderEntity(request.getId(),request.getCaution(), request.isPayed(), request.getStatus(),request.getVehicle(), request.getOrderDate());

    }

    public static OrderResponse entityToDto (OrderEntity orderEntity) { //Converte un oggetto OrderEntity in un oggetto OrderResponse
        OrderResponse response = new OrderResponse();
        response.setCaution(orderEntity.getCaution());
        response.setPayed(orderEntity.isPayed());
        response.setStatus(orderEntity.getStatus().toString());
        response.setOrderDate(orderEntity.getOrderDate());
        return response;
    }

    public static OrderEntity modelToEntity (OrderModel model) {
        return new OrderEntity(model.getOrderId(), model.getCaution(),model.isPayed(),model.getStatus(),model.getVehicleEntity(),model.getOrderDate());
    }

    public static OrderModel entityToModel (OrderEntity entity){
        return new OrderModel(entity.getId(),entity.getCaution(), entity.isPayed(), entity.getStatus(),entity.getVehicleEntity(),entity.getOrderDate());
    }
    public static OrderResponse modelToDto (OrderModel model) {
        OrderResponse response = new OrderResponse();
        response.setCaution(model.getCaution());
        response.setPayed(model.isPayed());
        response.setStatus(model.getStatus().toString());
        response.setOrderDate(model.getOrderDate());
        return response;
    }
    public static OrderModel dtoToModel (OrderResponse response) {
        OrderModel model = new OrderModel();
        model.setCaution(response.getCaution());
        model.setPayed(response.isPayed());
        model.setStatus(OrderStatus.convertStringToStatus(response.getStatus()));
        model.setOrderDate(response.getOrderDate());
        return model;
    }


}

