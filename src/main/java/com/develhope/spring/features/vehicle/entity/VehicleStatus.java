package com.develhope.spring.features.vehicle.entity;

public enum VehicleStatus {
    AVAILABLE,
    NOT_AVAILABLE,
    NOT_SET;

    public static VehicleStatus convertToStringToStatus(String status){
        return switch (status.toLowerCase()){
            case "available" -> VehicleStatus.AVAILABLE;
            case "not available" -> VehicleStatus.NOT_AVAILABLE;
            default -> VehicleStatus.NOT_SET;
        };
    }
}
