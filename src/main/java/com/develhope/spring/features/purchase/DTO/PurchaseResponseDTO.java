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
    private Long userId;
    private OffsetDateTime purchaseDate;

    public PurchaseResponseDTO(Long id, BigDecimal purchaseDeposit, Boolean isPayed, Long vehicleId, OffsetDateTime purchaseDate, Long userId) {
    }
}
