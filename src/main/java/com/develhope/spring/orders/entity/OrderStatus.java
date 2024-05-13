package com.develhope.spring.orders.entity;

public enum OrderStatus {
    PAYED,
    IN_PROGRESS,
    COMPLETED,
    NOT_SET;
    public static OrderStatus convertStringToStatus(String status) {
        return switch (status.toLowerCase()) {
            case "payed" -> OrderStatus.PAYED;
            case "in_progress" -> OrderStatus.IN_PROGRESS;
            case "completed" -> OrderStatus.COMPLETED;
            default -> OrderStatus.NOT_SET;
        };
    }
}
