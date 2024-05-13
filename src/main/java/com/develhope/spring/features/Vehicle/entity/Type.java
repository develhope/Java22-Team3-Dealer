package com.develhope.spring.features.Vehicle.entity;

public enum Type {
    VAN,
    CAR,
    SCOOTER,
    MOTORCYCLE,
    SUV,
    CAMPER,
    NOT_SET;

    public static Type convertStringToType (String type) {
        return switch (type.toLowerCase()) {
            case "van" -> Type.VAN;
            case "car" -> Type.CAR;
            case "scooter" -> Type.SCOOTER;
            case "motorcycle" -> Type.MOTORCYCLE;
            case "suv" -> Type.SUV;
            case "camper" -> Type.CAMPER;
            default -> Type.NOT_SET;
        };
    }
}
