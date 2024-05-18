package com.develhope.spring.features.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LinkUserVehicleRepository extends JpaRepository<LinkRentUserVehicle,Long> {
    List<LinkRentUserVehicle> findUserEntitiesByRentEntity_Id(Long rentId);

   Optional<LinkRentUserVehicle> findByRent_Id(Long rentId);

}
