package com.develhope.spring.features.orders.controller;

import com.develhope.spring.features.orders.entity.OrderVehicleLinkEntity;
import com.develhope.spring.features.orders.service.OrderVehicleLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-vehicle-links")
public class OrderVehicleLinkController {

    @Autowired
    private OrderVehicleLinkService orderVehicleLinkService;

    @GetMapping
    public ResponseEntity<List<OrderVehicleLinkEntity>> getAllOrderVehicleLinks() {
        List<OrderVehicleLinkEntity> orderVehicleLinks = orderVehicleLinkService.getAllOrderVehicleLinks();
        return new ResponseEntity<>(orderVehicleLinks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderVehicleLinkEntity> getOrderVehicleLinkById(@PathVariable Long id) {
        OrderVehicleLinkEntity orderVehicleLink = orderVehicleLinkService.getOrderVehicleLinkById(id);
        if (orderVehicleLink != null) {
            return new ResponseEntity<>(orderVehicleLink, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<OrderVehicleLinkEntity> createOrderVehicleLink(@RequestBody OrderVehicleLinkEntity orderVehicleLink) {
        OrderVehicleLinkEntity createdOrderVehicleLink = orderVehicleLinkService.createOrderVehicleLink(orderVehicleLink);
        return new ResponseEntity<>(createdOrderVehicleLink, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderVehicleLinkEntity> updateOrderVehicleLink(@PathVariable Long id, @RequestBody OrderVehicleLinkEntity updatedOrderVehicleLink) {
        OrderVehicleLinkEntity updatedLink = orderVehicleLinkService.updateOrderVehicleLink(id, updatedOrderVehicleLink);
        if (updatedLink != null) {
            return new ResponseEntity<>(updatedLink, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderVehicleLink(@PathVariable Long id) {
        orderVehicleLinkService.deleteOrderVehicleLink(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
