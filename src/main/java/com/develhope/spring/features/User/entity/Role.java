package com.develhope.spring.features.User.entity;

public enum Role {
    ADMIN,
    CUSTOMER,
    SALESMAN,
    NOT_SET;

    public static Role convertStringToRole(String role) {
        return switch (role.toLowerCase()) {
            case "admin" -> Role.ADMIN;
            case "salesman" -> Role.SALESMAN;
            case "customer" -> Role.CUSTOMER;
            default -> Role.NOT_SET;
        };
    }
}
