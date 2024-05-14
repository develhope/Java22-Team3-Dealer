package com.develhope.spring.vehicle.entity;

public enum VehicleStatus {
    AVAILABLE,
    NOT_AVAILABLE,
    NOT_SET;

    public static com.develhope.spring.vehicle.entity.VehicleStatus convertToStringToStatus(String status){
        return switch (status.toLowerCase()){
            case "available" -> com.develhope.spring.vehicle.entity.VehicleStatus.AVAILABLE;
            case "not available" -> com.develhope.spring.vehicle.entity.VehicleStatus.NOT_AVAILABLE;
            default -> com.develhope.spring.vehicle.entity.VehicleStatus.NOT_SET;
        };
    }
}
