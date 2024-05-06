package com.develhope.spring.vehicle.repository;

import com.develhope.spring.vehicle.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
}
