package com.develhope.spring.features.vehicle.DTOs;

import com.develhope.spring.features.vehicle.entity.Type;

import com.develhope.spring.features.vehicle.entity.VehicleStatus;
import lombok.*;

import java.math.BigDecimal;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponse {
    private Long vehicleId;
    private String brand;
    private String model;
    private int displacement;
    private String color;
    private int power;
    private String transmission;
    private Integer registrationYear;
    private String fullType;
    private BigDecimal price;
    private BigDecimal discount;
    private String accessories;
    private Boolean isNew;
    private VehicleStatus vehicleStatus;
    private Type vehicleType;
}
