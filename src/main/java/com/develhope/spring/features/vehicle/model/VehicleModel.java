package com.develhope.spring.features.vehicle.model;

import com.develhope.spring.features.vehicle.DTOs.VehicleRequest;
import com.develhope.spring.features.vehicle.DTOs.VehicleResponse;
import com.develhope.spring.features.vehicle.entity.VehicleType;

import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleModel {
    private Long vehicleId;
    private String brand;
    private String model;
    private Integer displacement;
    private String color;
    private Integer power;
    private String transmission;
    private Integer registrationYear;
    private String fullType;
    private BigDecimal price;
    private BigDecimal discount;
    private String accessories;
    private Boolean isNew;
    private com.develhope.spring.vehicle.entity.VehicleStatus vehicleStatus;
    private VehicleType vehicleType;



    public VehicleModel(String brand, String model, int displacement, String color, int power, String transmission, Integer registrationYear, String fullType, BigDecimal price, BigDecimal discount, String accessories, Boolean isNew, com.develhope.spring.vehicle.entity.VehicleStatus vehicleStatus, VehicleType vehicleType) {
    }

    public static VehicleResponse modelToDto(VehicleModel model){
        return new VehicleResponse(model.getVehicleId(), model.getBrand(),model.getModel(),model.getDisplacement(),model.getColor(), model.getPower(), model.getTransmission(), model.getRegistrationYear(), model.getFullType(),model.getPrice(),model.getDiscount(),model.getAccessories(),model.getIsNew(),model.getVehicleStatus(),model.getVehicleType());
    }
    public static VehicleModel dtoToModel(VehicleRequest dto){
        return new VehicleModel(dto.getBrand(),dto.getModel(),dto.getDisplacement(),dto.getColor(),dto.getPower(),dto.getTransmission(), dto.getRegistrationYear(), dto.getFullType(),dto.getPrice(),dto.getDiscount(),dto.getAccessories(),dto.getIsNew(),dto.getVehicleStatus(),dto.getVehicleType());
    }
    public static VehicleEntity modelToEntity(VehicleModel model){
        return new VehicleEntity(model.getVehicleId(), model.getBrand(),model.getModel(),model.getDisplacement(),model.getColor(), model.getPower(), model.getTransmission(), model.getRegistrationYear(), model.getFullType(),model.getPrice(),model.getDiscount(),model.getAccessories(),model.getIsNew(),model.getVehicleStatus(),model.getVehicleType());
    }
    public static VehicleModel entityToModel(VehicleEntity entity){
        return new VehicleModel(entity.getVehicleId(), entity.getBrand(),entity.getModel(),entity.getDisplacement(),entity.getColor(), entity.getPower(), entity.getTransmission(), entity.getRegistrationYear(), entity.getFullType(),entity.getPrice(),entity.getDiscount(),entity.getAccessories(),entity.getIsNew(),entity.getVehicleStatus(),entity.getVehicleType());
    }
}
