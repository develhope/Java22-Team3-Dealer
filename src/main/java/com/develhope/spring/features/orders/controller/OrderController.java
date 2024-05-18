package com.develhope.spring.features.orders.controller;



import com.develhope.spring.features.orders.DTOs.OrderRequest;
import com.develhope.spring.features.orders.DTOs.OrderResponse;
import com.develhope.spring.features.orders.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @Operation(summary = "create order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "A new order has been created"),
            @ApiResponse(responseCode = "400", description = "Bad requests!!!")})
    @PostMapping("/createOrder")
    public ResponseEntity<OrderResponse>createOrder (@RequestBody OrderRequest request){
        OrderResponse newOrder = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }
    @Operation(summary = "Update order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad requests!")})
    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<OrderResponse>updateOrder(@PathVariable Long id, @RequestBody OrderRequest request){
        OrderResponse updateOrder = orderService.updateOrder(id,request);
        return ResponseEntity.ok(updateOrder);
    }
    @Operation(summary = "Delete order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad requests!")})
    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Get order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OrderEntity found successfully"),
            @ApiResponse(responseCode = "404", description = "OrderEntity not found!")})
    @GetMapping("/getOrder/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id){
        OrderResponse order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }
    @Operation(summary = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All orders retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No orders found!")})
    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() throws Exception{
        List<OrderResponse> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

}