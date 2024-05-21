package com.develhope.spring.features.orders.controller;

import com.develhope.spring.features.orders.model.OrderModel;
import com.develhope.spring.features.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAllOrders(@AuthenticationPrincipal UserDetails userDetails) {
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        ResponseEntity<List<OrderModel>> response = orderService.getAllOrders(role);
        if (response.getStatusCode().isError()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error 403: You do not have permission to view all orders");
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        Long userId = Long.valueOf(userDetails.getUsername());
        ResponseEntity<OrderModel> response = orderService.getOrderById(id, role, userId);
        if (response.getStatusCode().isError()) {
            if (response.getStatusCode().equals(HttpStatus.FORBIDDEN)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error 403: You do not have permission to view this order");
            } else if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404: Order not found");
            }
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderModel orderModel, @AuthenticationPrincipal UserDetails userDetails) {
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        ResponseEntity<OrderModel> response = orderService.createOrder(orderModel, role);
        if (response.getStatusCode().isError()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error 403: Impossible to create new order");
        }
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderModel orderModel, @AuthenticationPrincipal UserDetails userDetails) {
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        Long userId = Long.valueOf(userDetails.getUsername());
        orderModel.setId(id);
        ResponseEntity<OrderModel> response = orderService.updateOrder(orderModel, role, userId);
        if (response.getStatusCode().isError()) {
            if (response.getStatusCode().equals(HttpStatus.FORBIDDEN)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error 403: You do not have permission to update this order");
            } else if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404: Order not found");
            }
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        Long userId = Long.valueOf(userDetails.getUsername());
        ResponseEntity<Void> response = orderService.deleteOrder(id, role, userId);
        if (response.getStatusCode().isError()) {
            if (response.getStatusCode().equals(HttpStatus.FORBIDDEN)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error 403: You do not have permission to delete this order");
            } else if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404: Order not found");
            }
        }
        return response;
    }
}
