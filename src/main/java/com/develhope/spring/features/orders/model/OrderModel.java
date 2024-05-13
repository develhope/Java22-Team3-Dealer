package com.develhope.spring.features.orders.model;

import com.develhope.spring.features.Vehicle.entity.Vehicle;
import com.develhope.spring.features.orders.DTOs.CreateOrderRequest;
import com.develhope.spring.features.orders.DTOs.OrderResponse;
import com.develhope.spring.features.orders.entity.Order;
import com.develhope.spring.features.orders.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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
    private OffsetDateTime orderDate;


    public static Order dtoToEntity(CreateOrderRequest request) { // Converte un oggetto CreateOrderRequest in un oggetto Order
        Order order = new Order();
        order.setCaution(request.getCaution());
        order.setPayed(request.isPayed());
        order.setStatus(OrderStatus.convertStringToStatus(request.getStatus()));
        order.setOrderDate(request.getOrderDate());
        return order;

    }

    public static OrderResponse entityToDto(Order order) { //Converte un oggetto Order in un oggetto OrderResponse
        OrderResponse response = new OrderResponse();
        response.setCaution(order.getCaution());
        response.setPayed(order.isPayed());
        response.setStatus(order.getStatus().toString());
        response.setOrderDate(order.getOrderDate());
        return response;
    }

    public static CreateOrderRequest entityDtoRequest(Order order) { //Converte un oggetto Order in un oggetto CreateOrderRequest
        CreateOrderRequest request = new CreateOrderRequest();
        request.setCaution(order.getCaution());
        request.setPayed(order.isPayed());
        request.setStatus(order.getStatus().toString());
        request.setOrderDate(order.getOrderDate());
        return request;
    }
    //TODO: modelToEntity entityToModel
}

