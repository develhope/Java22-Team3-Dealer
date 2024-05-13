package com.develhope.spring.features.Vehicle.model;

import com.develhope.spring.features.Vehicle.DTOs.CreateVehicleRequest;
import com.develhope.spring.features.Vehicle.DTOs.VehicleResponse;
import com.develhope.spring.features.Vehicle.entity.Type;

import com.develhope.spring.features.Vehicle.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class VehicleModel {
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
    private com.develhope.spring.vehicle.entity.VehicleStatus vehicleStatus;
    private Type vehicleType;



    public VehicleModel(String brand, String model, int displacement, String color, int power, String transmission, Integer registrationYear, String fullType, BigDecimal price, BigDecimal discount, String accessories, Boolean isNew, com.develhope.spring.vehicle.entity.VehicleStatus vehicleStatus, Type vehicleType) {
    }

    public static VehicleResponse modelToDto(VehicleModel model){
        return new VehicleResponse(model.getVehicleId(), model.getBrand(),model.getModel(),model.getDisplacement(),model.getColor(), model.getPower(), model.getTransmission(), model.getRegistrationYear(), model.getFullType(),model.getPrice(),model.getDiscount(),model.getAccessories(),model.getIsNew(),model.getVehicleStatus(),model.getVehicleType());
    }
    public static VehicleModel dtoToModel(CreateVehicleRequest dto){
        return new VehicleModel(dto.getBrand(),dto.getModel(),dto.getDisplacement(),dto.getColor(),dto.getPower(),dto.getTransmission(), dto.getRegistrationYear(), dto.getFullType(),dto.getPrice(),dto.getDiscount(),dto.getAccessories(),dto.getIsNew(),dto.getVehicleStatus(),dto.getVehicleType());
    }
    public static Vehicle modelToEntity(VehicleModel model){
        return new Vehicle(model.getVehicleId(), model.getBrand(),model.getModel(),model.getDisplacement(),model.getColor(), model.getPower(), model.getTransmission(), model.getRegistrationYear(), model.getFullType(),model.getPrice(),model.getDiscount(),model.getAccessories(),model.getIsNew(),model.getVehicleStatus(),model.getVehicleType());
    }
    public static VehicleModel entityToModel(Vehicle entity){
        return new VehicleModel(entity.getVehicleId(), entity.getBrand(),entity.getModel(),entity.getDisplacement(),entity.getColor(), entity.getPower(), entity.getTransmission(), entity.getRegistrationYear(), entity.getFullType(),entity.getPrice(),entity.getDiscount(),entity.getAccessories(),entity.getIsNew(),entity.getVehicleStatus(),entity.getVehicleType());
    }
}
