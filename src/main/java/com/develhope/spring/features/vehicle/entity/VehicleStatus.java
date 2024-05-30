package com.develhope.spring.features.vehicle.entity;

public enum VehicleStatus {
    AVAILABLE,
    NOT_AVAILABLE,
    NOT_SET;

    public static VehicleStatus convertToStringToStatus(String status){
        return switch (status.toLowerCase()){
            case "available" -> AVAILABLE;
            case "not available" -> NOT_AVAILABLE;
            default -> NOT_SET;
        };
    }
}
