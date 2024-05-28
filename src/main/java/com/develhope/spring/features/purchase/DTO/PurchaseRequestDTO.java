package com.develhope.spring.features.purchase.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseRequestDTO {
    private BigDecimal purchaseDeposit;
    private Boolean isPayed;
    private Long vehicleID;
    private OffsetDateTime orderDate;
}
