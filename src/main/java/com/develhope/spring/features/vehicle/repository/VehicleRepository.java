package com.develhope.spring.features.vehicle.repository;

import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

}

