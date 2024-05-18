package com.develhope.spring.features.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@EnableJpaRepositories
public interface LinkUserVehicleRepository extends JpaRepository<LinkRentUserVehicle,Long> {
    List<LinkRentUserVehicle> findUserEntitiesByRentEntity_Id(Long rentId);

//    @Query("SELECT * FROM linkUserVehicleRent luvr WHERE luvr.rent_id = rent.id ")
   Optional<LinkRentUserVehicle> findByRent_Id(Long rentId);
   Optional<LinkRentUserVehicle> findByVehicle_Id(Long vehicleId);
   Optional<LinkRentUserVehicle> findByUser_Id(Long UserId);
}
