package com.develhope.spring.Vehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private VehicleTypeEnum vehicleType;
    private String brand;
    private String model;
    private long cubicCapacity;
    private String gearbox;
    private OffsetDateTime registrationYear;
    private String feeding;
    private BigDecimal price;
    private boolean isDiscount;
    private String accessories;
    private StatusTypeEnum statusType;

}
