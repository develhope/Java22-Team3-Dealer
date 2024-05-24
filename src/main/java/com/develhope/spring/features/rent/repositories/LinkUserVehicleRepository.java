package com.develhope.spring.features.rent.repositories;

import com.develhope.spring.features.rent.entities.LinkRentUserVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface LinkUserVehicleRepository extends JpaRepository<LinkRentUserVehicleEntity, Long> {
    List<LinkRentUserVehicleEntity> findUserEntitiesByRentEntity_Id(Long rentId);

    @Query("SELECT * FROM `link user vehicles on rentals details` luvord Join rental r on r.id = luvord.rent_id ")
    Optional<LinkRentUserVehicleEntity> findByRent_Id(Long rentId);

    @Query("SELECT * FROM `link user vehicles on rentals details` luvord Join vehicle_entity ve on ve.vehicle_id = luvord.vehicle_id ")
    Optional<LinkRentUserVehicleEntity> findByVehicle_Id(Long vehicleId);

    @Query("SELECT * FROM `link user vehicles on rentals details` luvord Join user_entity ue on ue.user_id = luvord.user_id ")
    Optional<LinkRentUserVehicleEntity> findByUser_Id(Long UserId);
}
