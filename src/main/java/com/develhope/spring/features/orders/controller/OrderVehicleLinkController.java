package com.develhope.spring.features.orders.controller;

import com.develhope.spring.features.orders.entity.LinkOrderUserVehicleEntity;
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
    public ResponseEntity<List<LinkOrderUserVehicleEntity>> getAllOrderVehicleLinks() {
        List<LinkOrderUserVehicleEntity> orderVehicleLinks = orderVehicleLinkService.getAllOrderVehicleLinks();
        return new ResponseEntity<>(orderVehicleLinks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkOrderUserVehicleEntity> getOrderVehicleLinkById(@PathVariable Long id) {
        LinkOrderUserVehicleEntity orderVehicleLink = orderVehicleLinkService.getOrderVehicleLinkById(id);
        if (orderVehicleLink != null) {
            return new ResponseEntity<>(orderVehicleLink, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<LinkOrderUserVehicleEntity> createOrderVehicleLink(@RequestBody LinkOrderUserVehicleEntity orderVehicleLink) {
        LinkOrderUserVehicleEntity createdOrderVehicleLink = orderVehicleLinkService.createOrderVehicleLink(orderVehicleLink);
        return new ResponseEntity<>(createdOrderVehicleLink, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinkOrderUserVehicleEntity> updateOrderVehicleLink(@PathVariable Long id, @RequestBody LinkOrderUserVehicleEntity updatedOrderVehicleLink) {
        LinkOrderUserVehicleEntity updatedLink = orderVehicleLinkService.updateOrderVehicleLink(id, updatedOrderVehicleLink);
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
