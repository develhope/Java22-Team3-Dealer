package com.develhope.spring.Vehicle.repository;

import com.develhope.spring.Vehicle.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    @Query(value = "SELECT * FROM VEHICLE WHERE COLOR = ?1", nativeQuery = true)
    List<VehicleEntity> getAllVehicleByColor(String color);

    @Query(value = "SELECT * FROM VEHICLE WHERE PRICE >= ?1 OR PRICE <= ?2", nativeQuery = true)
    List<VehicleEntity> getAllVehicleByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    @Query(value = "SELECT * FROM VEHICLE WHERE BRAND = ?1", nativeQuery = true)
    List<VehicleEntity> getAllVehicleByBrand(String brand);

    @Query(value = "SELECT * FROM VEHICLE WHERE MODEL = ?1", nativeQuery = true)
    List<VehicleEntity> getAllVehicleByModel(String model);

    @Query(value = "SELECT * FROM VEHICLE WHERE vehicle_Type = ?1", nativeQuery = true)
    List<VehicleEntity> getAllVehicleByType (String vehicleType);

    @Query(value = "SELECT * FROM VEHICLE WHERE vehicle_Status = ?1", nativeQuery = true)
    List<VehicleEntity> getAllVehicleStatus (String vehicleStatus);

    @Query(value = "SELECT * FROM VEHICLE WHERE is_new = ?1", nativeQuery = true)
    List<VehicleEntity> getAllVehicleNew (Boolean isNew);
}

