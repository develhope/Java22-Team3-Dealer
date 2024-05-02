package com.develhope.spring.Vehicle.DTO;
import com.develhope.spring.Vehicle.entity.VehicleEntity;
import com.develhope.spring.Vehicle.entity.VehicleStatus;
import com.develhope.spring.Vehicle.entity.VehicleType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class VehicleModel {
    private Long id;
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

    public VehicleModel(Long id, String brand, String model, int displacement, String color, int power, String transmission, int registrationYear, String fullType, BigDecimal price, BigDecimal discount, String accessories, Boolean isNew, VehicleStatus vehicleStatus, VehicleType vehicleType) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.displacement = displacement;
        this.color = color;
        this.power = power;
        this.transmission = transmission;
        this.registrationYear = registrationYear;
        this.fullType = fullType;
        this.price = price;
        this.discount = discount;
        this.accessories = accessories;
        this.isNew = isNew;
        this.vehicleStatus = vehicleStatus;
        this.vehicleType = vehicleType;
    }

    public VehicleModel(String brand, String model, int displacement, String color, int power, String transmission, int registrationYear, String fullType, BigDecimal price, BigDecimal discount, String accessories, Boolean isNew, VehicleStatus vehicleStatus, VehicleType vehicleType) {
        this.brand = brand;
        this.model = model;
        this.displacement = displacement;
        this.color = color;
        this.power = power;
        this.transmission = transmission;
        this.registrationYear = registrationYear;
        this.fullType = fullType;
        this.price = price;
        this.discount = discount;
        this.accessories = accessories;
        this.isNew = isNew;
        this.vehicleStatus = vehicleStatus;
        this.vehicleType = vehicleType;
    }

    public static VehicleDTO modelToDto(VehicleModel model) {
        return new VehicleDTO(model.getId(), model.getBrand(), model.getModel(), model.getDisplacement(), model.getColor(), model.getPower(), model.getTransmission(), model.getRegistrationYear(), model.getFullType(), model.getPrice(), model.getDiscount(), model.getAccessories(), model.getIsNew(), model.getVehicleStatus(), model.getVehicleType());
    }

    public static VehicleModel dtoToModel(CreateVehicleRequest request) {
        return new VehicleModel(request.getBrand(), request.getModel(), request.getDisplacement(), request.getColor(), request.getPower(), request.getTransmission(), request.getRegistrationYear(), request.getFullType(), request.getPrice(), request.getDiscount(), request.getAccessories(), request.getIsNew(), request.getVehicleStatus(), request.getVehicleType());
    }

    public static VehicleEntity modelToEntity(VehicleModel model) {
        return new VehicleEntity(model.getId(), model.getBrand(), model.getModel(), model.getDisplacement(), model.getColor(), model.getPower(), model.getTransmission(), model.getRegistrationYear(), model.getFullType(), model.getPrice(), model.getDiscount(), model.getAccessories(), model.getIsNew(), model.getVehicleStatus(), model.getVehicleType());
    }

    public static VehicleModel entityToModel(VehicleEntity entity) {
        return new VehicleModel(entity.getVehicleId(), entity.getBrand(), entity.getModel(), entity.getDisplacement(), entity.getColor(), entity.getPower(), entity.getTransmission(), entity.getRegistrationYear(), entity.getFullType(), entity.getPrice(), entity.getDiscount(), entity.getAccessories(), entity.getIsNew(), entity.getVehicleStatus(), entity.getVehicleType());
    }
}
