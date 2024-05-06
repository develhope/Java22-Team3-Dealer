package com.develhope.spring.vehicle.DTOs;

import com.develhope.spring.vehicle.entity.Status;
import com.develhope.spring.vehicle.entity.Type;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleRequest {
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
