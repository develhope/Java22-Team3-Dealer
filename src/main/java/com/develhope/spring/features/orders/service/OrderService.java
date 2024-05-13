package com.develhope.spring.features.orders.service;

import com.develhope.spring.features.orders.DTOs.CreateOrderRequest;
import com.develhope.spring.features.orders.DTOs.OrderResponse;
import com.develhope.spring.features.orders.entity.Order;
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
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new Exception("No orders found in the database.");
        }

        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            OrderResponse orderResponse = OrderModel.entityToDto(order);
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

    public OrderResponse createOrder(CreateOrderRequest request) {
        Order order = OrderModel.dtoToEntity(request);
        Order savedOrder = orderRepository.save(order);
        return OrderModel.entityToDto(savedOrder);

    }

    public OrderResponse findById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found for id: " + orderId));
        return OrderModel.entityToDto(order);
    }
    public OrderResponse updateOrder(Long orderId, CreateOrderRequest request) {
        Order orderToUpdate = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found for id: " + orderId));

        orderToUpdate.setCaution(request.getCaution());
        orderToUpdate.setPayed(request.isPayed());
        if (request.getStatus() != null) {
            orderToUpdate.setStatus(OrderStatus.convertStringToStatus(request.getStatus()));
        }
        Order savedOrder = orderRepository.save(orderToUpdate);

        // Converte l'ordine salvato in un DTO e lo restituisce
        return OrderModel.entityToDto(savedOrder);
    }

    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
