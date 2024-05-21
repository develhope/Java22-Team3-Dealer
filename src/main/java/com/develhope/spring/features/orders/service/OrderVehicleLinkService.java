package com.develhope.spring.features.orders.service;

import com.develhope.spring.features.orders.entity.LinkOrderUserVehicleEntity;
import com.develhope.spring.features.orders.repository.OrderVehicleLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderVehicleLinkService {

    @Autowired
    private OrderVehicleLinkRepository orderVehicleLinkRepository;

    public List<LinkOrderUserVehicleEntity> getAllOrderVehicleLinks() {
        return orderVehicleLinkRepository.findAll();
    }

    public LinkOrderUserVehicleEntity getOrderVehicleLinkById(Long id) {
        Optional<LinkOrderUserVehicleEntity> optionalOrderVehicleLink = orderVehicleLinkRepository.findById(id);
        return optionalOrderVehicleLink.orElse(null);
    }

    public LinkOrderUserVehicleEntity createOrderVehicleLink(LinkOrderUserVehicleEntity orderVehicleLink) {
        return orderVehicleLinkRepository.save(orderVehicleLink);
    }

    public LinkOrderUserVehicleEntity updateOrderVehicleLink(Long id, LinkOrderUserVehicleEntity updatedOrderVehicleLink) {
        if (orderVehicleLinkRepository.existsById(id)) {
            updatedOrderVehicleLink.setId(id);
            return orderVehicleLinkRepository.save(updatedOrderVehicleLink);
        } else {
            return null; // Restituisci null se il collegamento non esiste
        }
    }

    public void deleteOrderVehicleLink(Long id) {
        orderVehicleLinkRepository.deleteById(id);
    }
}
