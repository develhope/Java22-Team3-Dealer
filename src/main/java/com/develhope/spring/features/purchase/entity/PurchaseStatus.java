package com.develhope.spring.features.purchase.entity;

public enum PurchaseStatus {
    PAYED,
    IN_PROGRESS,
    COMPLETED,
    DELETED,
    NOT_SET;
    public static PurchaseStatus convertStringToStatus(String status) {
        return switch (status.toLowerCase()) {
            case "payed" -> PurchaseStatus.PAYED;
            case "in_progress" -> PurchaseStatus.IN_PROGRESS;
            case "completed" -> PurchaseStatus.COMPLETED;
            case "deleted" -> PurchaseStatus.DELETED;
            default -> PurchaseStatus.NOT_SET;
        };
    }
}
