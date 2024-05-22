package com.develhope.spring.features.vehicle.entity;

public enum VehicleType {
    VAN,
    CAR,
    SCOOTER,
    MOTORCYCLE,
    SUV,
    CAMPER,
    NOT_SET;

    public static VehicleType convertStringToType (String type) {
        return switch (type.toLowerCase()) {
            case "van" -> VehicleType.VAN;
            case "car" -> VehicleType.CAR;
            case "scooter" -> VehicleType.SCOOTER;
            case "motorcycle" -> VehicleType.MOTORCYCLE;
            case "suv" -> VehicleType.SUV;
            case "camper" -> VehicleType.CAMPER;
            default -> VehicleType.NOT_SET;
        };
    }
}
