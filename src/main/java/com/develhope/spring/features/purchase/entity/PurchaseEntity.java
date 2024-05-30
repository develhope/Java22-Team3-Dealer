package com.develhope.spring.features.purchase.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "purchase")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true, name = "deposit")
    private BigDecimal purchaseDeposit;
    @Column(nullable = false, name = "purchase_paid")
    private Boolean isPayed;
    @Column(nullable = false, name = "purchaseDate")
    private LocalDateTime purchaseDate;
    @Column(nullable = false, name = "costumer_id")
    private Long costumerId;
    @Column(nullable = false, name = "seller_id")
    private Long sellerId;
    @Column(nullable = false, name = "vehicle_id")
    private Long vehicleId;

}