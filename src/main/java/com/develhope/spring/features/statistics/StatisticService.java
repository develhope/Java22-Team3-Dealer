package com.develhope.spring.features.statistics;

import com.develhope.spring.features.orders.repository.OrderRepository;
import com.develhope.spring.features.orders.repository.OrderVehicleLinkRepository;
import com.develhope.spring.features.purchase.entity.PurchaseEntity;
import com.develhope.spring.features.purchase.repository.LinkUserVehiclePurchaseRepository;
import com.develhope.spring.features.purchase.repository.PurchaseRepository;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import com.develhope.spring.features.vehicle.entity.VehicleStatus;
import com.develhope.spring.features.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LinkUserVehiclePurchaseRepository purchaseRepository;

    @Autowired
    private OrderVehicleLinkRepository orderRepository;

    // Ottenere il veicolo più venduto in un dato periodo
    public VehicleEntity getTopSoldVehicleInPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return purchaseRepository.findTopSoldVehicleInPeriod(startDate, endDate).stream().findFirst().orElse(null);
    }

    // Ottenere il veicolo con valore più alto venduto fino a quel dato istante
    public VehicleEntity getMostValuableSoldVehicle() {
        return purchaseRepository.findMostValuableSoldVehicle().stream().findFirst().orElse(null);
    }

    // Ottenere il veicolo più ricercato/ordinato
    public VehicleEntity getMostOrderedVehicle() {
        return orderRepository.findMostOrderedVehicle().stream().findFirst().orElse(null);
    }

    // Verificare quante vendite ha fatto un venditore in un determinato periodo
    public List<PurchaseEntity> getSalesBySellerInPeriod(Long sellerId, LocalDateTime startDate, LocalDateTime endDate) {
        return purchaseRepository.findSalesBySellerInPeriod(sellerId, startDate, endDate);
    }

    // Verificare quanti soldi ha generato un venditore in un determinato periodo
    public Double getRevenueBySellerInPeriod(Long sellerId, LocalDateTime startDate, LocalDateTime endDate) {
        return purchaseRepository.findRevenueBySellerInPeriod(sellerId, startDate, endDate);
    }

    // Verificare il guadagno del salone in un determinato periodo
    public Double getTotalRevenueInPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return purchaseRepository.findTotalRevenueInPeriod(startDate, endDate);
    }

    // Verificare i veicoli attualmente ordinabili/acquistabili
    public List<VehicleEntity> getAvailableVehicles() {
        List<VehicleEntity> vehicles = vehicleRepository.findAll();
        List<VehicleEntity> available = new ArrayList<>();
        List<VehicleEntity> notAvailable = new ArrayList<>();
        for(VehicleEntity vehicle : vehicles){
            if(vehicle.getVehicleStatus() == VehicleStatus.AVAILABLE){
                available.add(vehicle);
                return available;
            } else {
                notAvailable.add(vehicle);
                return notAvailable;
            }
        }
        return available;
    }

    public List<VehicleEntity> getNewVehicles() {
        return vehicleRepository.findAll().stream().filter(VehicleEntity::getIsNew).collect(Collectors.toList());
    }

    public List<VehicleEntity> getUsedVehicles() {
        return vehicleRepository.findAll().stream().filter(vehicle -> !vehicle.getIsNew()).collect(Collectors.toList());
    }
}
