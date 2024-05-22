package com.develhope.spring.features.purchase.DTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponseDTO {
    private Long id;
    private BigDecimal purchaseDeposit;
    private boolean isPayed;
    private Long vehicleId;
    private OffsetDateTime purchaseDate;
}
