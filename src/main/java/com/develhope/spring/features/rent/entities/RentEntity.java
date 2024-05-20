package com.develhope.spring.features.rent.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Table(name = "Rental")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true, name = "deposit")
    private BigDecimal rentalDeposit;
    @Column(nullable = false, name = "daily rental costs")
    private BigDecimal dailyRental;
    @Column(nullable = true, name = "total rental costs")
    private BigDecimal totalRent;
    @Column(nullable = false, name = "rental start date")
    private OffsetDateTime rentalStart;
    @Column(nullable = false, name = " rental end date")
    private OffsetDateTime rentalEnd;
    @Column(nullable = false, name = "has been the rental costs payed?")
    private Boolean isPayed;
    @Column(nullable = false, name = "costumer_id")
    private Long costumerId;
    @Column(nullable = false, name = "seller_id")
    private Long sellerId;
    @Column(nullable = false, name = "vehicle_id")
    private Long vehicleId;

}

