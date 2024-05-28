package com.develhope.spring.features.orders.controller;

import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.orders.model.OrderModel;
import com.develhope.spring.features.orders.service.OrderService;
import com.develhope.spring.features.user.entity.UserEntity;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAllOrders(@AuthenticationPrincipal UserEntity user) {
        String role = user.getRole().toString();
        Either<GenericErrors, List<OrderModel>> result = orderService.getAllOrders(role);
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), "Access denied"), HttpStatus.valueOf(error.getCode())),
                orders -> new ResponseEntity<>(orders, HttpStatus.OK)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id, @AuthenticationPrincipal UserEntity user) {
        String role = user.getRole().toString();
        Either<GenericErrors, OrderModel> result = orderService.getOrderById(id, role, user.getUserId());
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), "Order not found"), HttpStatus.valueOf(error.getCode())),
                order -> new ResponseEntity<>(order, HttpStatus.OK)
        );
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderModel orderModel, @AuthenticationPrincipal UserEntity user) {
        String role = user.getRole().toString();
        Either<GenericErrors, OrderModel> result = orderService.createOrder(orderModel, role);
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), "Error creating order"), HttpStatus.valueOf(error.getCode())),
                createdOrder -> new ResponseEntity<>(createdOrder, HttpStatus.CREATED)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderModel orderModel, @AuthenticationPrincipal UserEntity user) {
        String role = user.getRole().toString();
        Either<GenericErrors, OrderModel> result = orderService.updateOrder(orderModel, role, user.getUserId());
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), "Error updating order"), HttpStatus.valueOf(error.getCode())),
                updatedOrder -> new ResponseEntity<>(updatedOrder, HttpStatus.OK)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id, @AuthenticationPrincipal UserEntity user) {
        String role = user.getRole().toString();
        Either<GenericErrors, Void> result = orderService.deleteOrder(id, role, user.getUserId());
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), "Error deleting order"), HttpStatus.valueOf(error.getCode())),
                deleted -> new ResponseEntity<>(new GenericErrors(200, "Order deleted successfully"), HttpStatus.OK)
        );
    }
}
