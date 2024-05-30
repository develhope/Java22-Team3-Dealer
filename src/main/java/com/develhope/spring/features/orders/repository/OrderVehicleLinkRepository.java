package com.develhope.spring.features.orders.repository;


import com.develhope.spring.features.orders.entity.LinkOrderUserVehicleEntity;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderVehicleLinkRepository extends JpaRepository<LinkOrderUserVehicleEntity, Long> {

    @Query(value = "SELECT l FROM LinkOrderUserVehicleEntity l INNER JOIN l.orderEntity o WHERE o.id = :orderId", nativeQuery = true)
    Optional<LinkOrderUserVehicleEntity> findByOrder_Id(Long orderId);

    @Query(value = "SELECT l FROM LinkOrderUserVehicleEntity l INNER JOIN l.userEntity u WHERE u.id = :userId", nativeQuery = true)
    Collection<LinkOrderUserVehicleEntity> findByUser_Id(Long userId);

    @Query(value = "SELECT l FROM LinkOrderUserVehicleEntity l INNER JOIN l.vehicleEntity v WHERE v.id = :vehicleId", nativeQuery = true)
    Optional<LinkOrderUserVehicleEntity> findByVehicle_Id(Long vehicleId);

    @Query(value = "SELECT v FROM Order o JOIN o.vehicle v GROUP BY v ORDER BY COUNT(v) DESC", nativeQuery = true)
    List<VehicleEntity> findMostOrderedVehicle();
}