package com.develhope.spring.Vehicle;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Vehicle {
    private long id;
    private VehicleTypeEnum vehicleType;
    private String brand;
    private String model;
    private long cubicCapacity;
    private String gearbox;
    private OffsetDateTime registrationYear;
    private String feeding;
    private BigDecimal price;
    private boolean isDiscount;
    private String accessories;
    private StatusTypeEnum statusType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VehicleTypeEnum getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypeEnum vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getCubicCapacity() {
        return cubicCapacity;
    }

    public void setCubicCapacity(long cubicCapacity) {
        this.cubicCapacity = cubicCapacity;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public OffsetDateTime getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(OffsetDateTime registrationYear) {
        this.registrationYear = registrationYear;
    }

    public String getFeeding() {
        return feeding;
    }

    public void setFeeding(String feeding) {
        this.feeding = feeding;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public StatusTypeEnum getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusTypeEnum statusType) {
        this.statusType = statusType;
    }
}
