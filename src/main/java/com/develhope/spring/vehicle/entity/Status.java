package com.develhope.spring.vehicle.entity;

public enum Status {
    AVAILABLE,
    NOT_AVAILABLE,
    NOT_SET;

    public static Status convertToStringToStatus(String status){
        return switch (status.toLowerCase()){
            case "available" -> Status.AVAILABLE;
            case "not available" -> Status.NOT_AVAILABLE;
            default -> Status.NOT_SET;
        };
    }
}
