package com.develhope.spring.Vehicle.DTOs;

import com.develhope.spring.Vehicle.entity.Type;
import com.develhope.spring.vehicle.entity.Status;

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
    private Status vehicleStatus;
    private Type vehicleType;
}
