package com.develhope.spring.features.orders.entity;

public enum OrderStatus {
    PAYED,
    IN_PROGRESS,
    COMPLETED,
    DELETED,
    NOT_SET;

    public static OrderStatus convertStringToStatus(String status) {
        return switch (status.toLowerCase()) {
            case "payed" -> OrderStatus.PAYED;
            case "in_progress" -> OrderStatus.IN_PROGRESS;
            case "completed" -> OrderStatus.COMPLETED;
            case "deleted" -> OrderStatus.DELETED;
            default -> OrderStatus.NOT_SET;
        };
    }
}
