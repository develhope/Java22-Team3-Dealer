package com.develhope.spring.features.rent.DTOs;

import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponseDTO {
    private Long id;
    private BigDecimal rentalDeposit;
    private BigDecimal dailyRental;
    private BigDecimal totalRent;
    private OffsetDateTime rentalStart;
    private OffsetDateTime rentalEnd;
    private Boolean isPayed;
}
