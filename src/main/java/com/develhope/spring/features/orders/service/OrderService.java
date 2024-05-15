package com.develhope.spring.features.orders.service;

import com.develhope.spring.features.orders.DTOs.OrderRequest;
import com.develhope.spring.features.orders.DTOs.OrderResponse;
import com.develhope.spring.features.orders.entity.OrderEntity;
import com.develhope.spring.features.orders.entity.OrderStatus;
import com.develhope.spring.features.orders.model.OrderModel;
import com.develhope.spring.features.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    public List<OrderResponse> getAllOrders() throws Exception {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        if (orderEntities.isEmpty()) {
            throw new Exception("No orderEntities found in the database.");
        }

        List<OrderResponse> orderResponses = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            OrderResponse orderResponse = OrderModel.entityToDto(orderEntity);
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

    public OrderResponse createOrder(OrderRequest request) {
        OrderEntity orderEntity = OrderModel.dtoToEntity(request);
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        return OrderModel.entityToDto(savedOrderEntity);

    }

    public OrderResponse findById(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("OrderEntity not found for id: " + orderId));
        return OrderModel.entityToDto(orderEntity);
    }
    public OrderResponse updateOrder(Long orderId, OrderRequest request) {
        OrderEntity orderEntityToUpdate = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("OrderEntity not found for id: " + orderId));

        orderEntityToUpdate.setCaution(request.getCaution());
        orderEntityToUpdate.setPayed(request.isPayed());
        if (request.getStatus() != null) {
            orderEntityToUpdate.setStatus(OrderStatus.convertStringToStatus(String.valueOf(request.getStatus())));
        }
        OrderEntity savedOrderEntity = orderRepository.save(orderEntityToUpdate);

        // Converte l'ordine salvato in un DTO e lo restituisce
        return OrderModel.entityToDto(savedOrderEntity);
    }

    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}