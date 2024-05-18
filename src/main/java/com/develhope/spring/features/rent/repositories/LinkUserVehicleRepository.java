package com.develhope.spring.features.rent.repositories;

import com.develhope.spring.features.rent.LinkRentUserVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@EnableJpaRepositories
public interface LinkUserVehicleRepository extends JpaRepository<LinkRentUserVehicleEntity,Long> {
    List<LinkRentUserVehicleEntity> findUserEntitiesByRentEntity_Id(Long rentId);

//    @Query("SELECT * FROM linkUserVehicleRent luvr WHERE luvr.rent_id = rent.id ")
   Optional<LinkRentUserVehicleEntity> findByRent_Id(Long rentId);
   Optional<LinkRentUserVehicleEntity> findByVehicle_Id(Long vehicleId);
   Optional<LinkRentUserVehicleEntity> findByUser_Id(Long UserId);
}
