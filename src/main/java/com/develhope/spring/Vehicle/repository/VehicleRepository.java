package com.develhope.spring.Vehicle.repository;

import com.develhope.spring.Vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "SELECT * FROM VEHICLE WHERE COLOR = ?1", nativeQuery = true)
    List<Vehicle> getAllVehicleByColor(String color);

    @Query(value = "SELECT * FROM VEHICLE WHERE PRICE >= ?1 OR PRICE <= ?2", nativeQuery = true)
    List<Vehicle> getAllVehicleByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    @Query(value = "SELECT * FROM VEHICLE WHERE BRAND = ?1", nativeQuery = true)
    List<Vehicle> getAllVehicleByBrand(String brand);

    @Query(value = "SELECT * FROM VEHICLE WHERE MODEL = ?1", nativeQuery = true)
    List<Vehicle> getAllVehicleByModel(String model);

    @Query(value = "SELECT * FROM VEHICLE WHERE vehicle_Type = ?1", nativeQuery = true)
    List<Vehicle> getAllVehicleByType (String vehicleType);

    @Query(value = "SELECT * FROM VEHICLE WHERE vehicle_Status = ?1", nativeQuery = true)
    List<Vehicle> getAllVehicleStatus (String vehicleStatus);

    @Query(value = "SELECT * FROM VEHICLE WHERE is_new = ?1", nativeQuery = true)
    List<Vehicle> getAllVehicleNew (Boolean isNew);
}

