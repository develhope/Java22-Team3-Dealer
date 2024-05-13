package com.develhope.spring.features.purchase.entity;

import com.develhope.spring.features.Vehicle.entity.Vehicle;
import com.develhope.spring.features.orders.entity.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "purchases")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Purchase {

    //Per ogni acquisto avremo:

    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "deposit")
    private BigDecimal deposit;

    @Column(nullable = false, name = "payed")
    private boolean payed;

    @Column(nullable = false, name = "status")
    private OrderStatus status;

    @Column(nullable = false, name = "vehicle")
    private Vehicle vehicle;

    @Column(nullable = false, name = "purchaseDate")
    private OffsetDateTime purchaseDate;
}
