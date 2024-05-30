package com.develhope.spring.features.purchase.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponseDTO {
    private Long id;
    private BigDecimal purchaseDeposit;
    private Boolean isPayed;
    private LocalDateTime purchaseDate;
    private Long costumerId;
    private Long sellerId;
    private Long vehicleId;
}
