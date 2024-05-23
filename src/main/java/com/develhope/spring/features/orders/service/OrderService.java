package com.develhope.spring.features.orders.service;

import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.orders.entity.OrderEntity;
import com.develhope.spring.features.orders.model.OrderModel;
import com.develhope.spring.features.orders.repository.OrderRepository;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Either<GenericErrors, List<OrderModel>> getAllOrders(String role) {
        if (role.equals("ROLE_ADMIN") || role.equals("ROLE_SALESMAN")) {
            List<OrderEntity> orderEntities = orderRepository.findAll();
            return Either.right(OrderModel.entityListToModelList(orderEntities));
        } else {
            return Either.left(new GenericErrors(403, "Accesso vietato"));
        }
    }

    public Either<GenericErrors, OrderModel> getOrderById(Long id, String role, Long userId) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (orderEntityOptional.isPresent()) {
            OrderEntity orderEntity = orderEntityOptional.get();
            if (role.equals("ROLE_ADMIN") || role.equals("ROLE_SALESMAN") || (role.equals("ROLE_CUSTOMER") && orderEntity.getCustomerId().equals(userId))) {
                return Either.right(OrderModel.entityToModel(orderEntity));
            } else {
                return Either.left(new GenericErrors(403, "Accesso vietato"));
            }
        } else {
            return Either.left(new GenericErrors(404, "Ordine non trovato"));
        }
    }

    public Either<GenericErrors, OrderModel> createOrder(OrderModel orderModel, String role) {
        if (role.equals("ROLE_ADMIN") || role.equals("ROLE_SALESMAN")) {
            try {
                OrderEntity orderEntity = OrderModel.modelToEntity(orderModel);
                orderEntity = orderRepository.save(orderEntity);
                return Either.right(OrderModel.entityToModel(orderEntity));
            } catch (Exception e) {
                return Either.left(new GenericErrors(500, "Error creating order: " + e.getMessage()));
            }
        } else {
            return Either.left(new GenericErrors(403, "Access denied"));
        }
    }

    public Either<GenericErrors, OrderModel> updateOrder(OrderModel orderModel, String role, Long userId) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderModel.getId());
        if (orderEntityOptional.isPresent()) {
            OrderEntity orderEntity = orderEntityOptional.get();
            if (role.equals("ROLE_ADMIN") || role.equals("ROLE_SALESMAN") || (role.equals("ROLE_CUSTOMER") && orderEntity.getCustomerId().equals(userId))) {
                try {
                    OrderEntity updatedOrderEntity = OrderModel.modelToEntity(orderModel);
                    updatedOrderEntity = orderRepository.save(updatedOrderEntity);
                    return Either.right(OrderModel.entityToModel(updatedOrderEntity));
                } catch (Exception e) {
                    return Either.left(new GenericErrors(500, "Error updating order: " + e.getMessage()));
                }
            } else {
                return Either.left(new GenericErrors(403, "Access denied"));
            }
        } else {
            return Either.left(new GenericErrors(404, "Ordine non trovato"));
        }
    }

    public Either<GenericErrors, Void> deleteOrder(Long id, String role, Long userId) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (orderEntityOptional.isPresent()) {
            OrderEntity orderEntity = orderEntityOptional.get();
            if (role.equals("ROLE_ADMIN") || role.equals("ROLE_SALESMAN") || (role.equals("ROLE_CUSTOMER") && orderEntity.getCustomerId().equals(userId))) {
                try {
                    orderRepository.deleteById(id);
                    return Either.right(null);
                } catch (Exception e) {
                    return Either.left(new GenericErrors(500, "Error deleting order: " + e.getMessage()));
                }
            } else {
                return Either.left(new GenericErrors(403, "Access denied"));
            }
        } else {
            return Either.left(new GenericErrors(404, "Order not found"));
        }
    }
}
