package com.develhope.spring.features.orders.service;

import com.develhope.spring.features.orders.entity.OrderEntity;
import com.develhope.spring.features.orders.model.OrderModel;
import com.develhope.spring.features.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<List<OrderModel>> getAllOrders(String role) {
        if (role.equals("ROLE_ADMIN") || role.equals("ROLE_SALESMAN")) {
            List<OrderEntity> orderEntities = orderRepository.findAll();
            return new ResponseEntity<>(OrderModel.entityListToModelList(orderEntities), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<OrderModel> getOrderById(Long id, String role, Long userId) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (orderEntityOptional.isPresent()) {
            OrderEntity orderEntity = orderEntityOptional.get();
            if (role.equals("ROLE_ADMIN") || role.equals("ROLE_SALESMAN") || (role.equals("ROLE_CUSTOMER") && orderEntity.getCustomerId().equals(userId))) {
                return new ResponseEntity<>(OrderModel.entityToModel(orderEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<OrderModel> createOrder(OrderModel orderModel, String role) {
        if (role.equals("ROLE_ADMIN") || role.equals("ROLE_SALESMAN")) {
            OrderEntity orderEntity = OrderModel.modelToEntity(orderModel);
            orderEntity = orderRepository.save(orderEntity);
            return new ResponseEntity<>(OrderModel.entityToModel(orderEntity), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<OrderModel> updateOrder(OrderModel orderModel, String role, Long userId) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderModel.getId());
        if (orderEntityOptional.isPresent()) {
            OrderEntity orderEntity = orderEntityOptional.get();
            if (role.equals("ROLE_ADMIN") || role.equals("ROLE_SALESMAN") || (role.equals("ROLE_CUSTOMER") && orderEntity.getCustomerId().equals(userId))) {
                OrderEntity updatedOrderEntity = OrderModel.modelToEntity(orderModel);
                updatedOrderEntity = orderRepository.save(updatedOrderEntity);
                return new ResponseEntity<>(OrderModel.entityToModel(updatedOrderEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> deleteOrder(Long id, String role, Long userId) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (orderEntityOptional.isPresent()) {
            OrderEntity orderEntity = orderEntityOptional.get();
            if (role.equals("ROLE_ADMIN") || role.equals("ROLE_SALESMAN") || (role.equals("ROLE_CUSTOMER") && orderEntity.getCustomerId().equals(userId))) {
                orderRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
