package com.develhope.spring.Entities.User;

public enum UserTypeEnum {
    ADMIN,
    CUSTOMER,
    SALESMAN,
    NOT_SET;

    public static UserTypeEnum convertStringToRole(String role) {
        return switch (role.toLowerCase()) {
            case "admin" -> UserTypeEnum.ADMIN;
            case "salesman" -> UserTypeEnum.SALESMAN;
            case "customer" -> UserTypeEnum.CUSTOMER;
            default -> UserTypeEnum.NOT_SET;
        };
    }
}
