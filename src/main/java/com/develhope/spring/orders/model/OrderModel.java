package com.develhope.spring.orders.model;

import com.develhope.spring.orders.DTOs.CreateOrderRequest;
import com.develhope.spring.orders.DTOs.OrderResponse;
import com.develhope.spring.orders.entity.Order;
import com.develhope.spring.orders.entity.OrderStatus;
import com.develhope.spring.vehicle.entity.Vehicle;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    @NotNull
    private BigDecimal caution;
    @NotNull
    private boolean payed;
    @NotNull
    private OrderStatus status;
    @NotNull
    private Vehicle vehicle;


    public static Order dtoToEntity(CreateOrderRequest request) { // Converte un oggetto CreateOrderRequest in un oggetto Order
        Order order = new Order();
        order.setCaution(request.getCaution());
        order.setPayed(request.isPayed());
        order.setStatus(OrderStatus.convertStringToStatus(request.getStatus()));
        return order;

    }
    public static OrderResponse entityToDto(Order order){ //Converte un oggetto Order in un oggetto OrderResponse
        OrderResponse response = new OrderResponse();
        response.setCaution(order.getCaution());
        response.setPayed(order.isPayed());
        response.setStatus(order.getStatus().toString());
        response.setVehicleId(order.getVehicle().getVehicleId());
        return response;
    }
    public static CreateOrderRequest entityDtoRequest(Order order){ //Converte un oggetto Order in un oggetto CreateOrderRequest
        CreateOrderRequest request = new CreateOrderRequest();
        request.setCaution(order.getCaution());
        request.setPayed(order.isPayed());
        request.setStatus(order.getStatus().toString());
        request.setVehicleID(order.getVehicle().getVehicleId());
        return request;
}
}

