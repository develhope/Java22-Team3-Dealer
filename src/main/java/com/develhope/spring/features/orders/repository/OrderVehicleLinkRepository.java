package com.develhope.spring.features.orders.repository;


import com.develhope.spring.features.orders.entity.LinkOrderUserVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderVehicleLinkRepository extends JpaRepository<LinkOrderUserVehicleEntity, Long> {

}