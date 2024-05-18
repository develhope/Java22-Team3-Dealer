package com.develhope.spring.features.vehicle.entity;
import jakarta.persistence.*;
import lombok.*;
import com.develhope.spring.vehicle.entity.VehicleStatus;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Integer displacement;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private Integer power;
    @Column(nullable = false)
    private String transmission;
    @Column(nullable = false)
    private Integer registrationYear;
    @Column(nullable = false)
    private String fullType;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private BigDecimal discount;
    @Column(nullable = false)
    private String accessories;
    @Column(nullable = false)
    private Boolean isNew;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

}