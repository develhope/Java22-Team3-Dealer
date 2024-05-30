package com.develhope.spring.features.purchase.DTO;

import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseRequestDTO {
    private BigDecimal purchaseDeposit;
    private Boolean isPayed;
    private Long vehicleID;
    private Long userId;
    private OffsetDateTime orderDate;
}
