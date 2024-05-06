package com.develhope.spring.Vehicle.DTO;

import com.develhope.spring.Vehicle.entity.VehicleStatus;
import com.develhope.spring.Vehicle.entity.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleRequest {
    private String brand;
    private String model;
    private int displacement;
    private String color;
    private int power;
    private String transmission;
    private int registrationYear;
    private String fullType;
    private BigDecimal price;
    private BigDecimal discount;
    private String accessories;
    private Boolean isNew;
    private VehicleStatus vehicleStatus;
    private VehicleType vehicleType;
}
