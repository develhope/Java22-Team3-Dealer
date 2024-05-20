package com.develhope.spring.features.rent.DTOs;

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
    private Long costumerId;
    private Long sellerId;
    private Long vehicleId;
}
