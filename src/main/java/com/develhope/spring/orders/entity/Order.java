package com.develhope.spring.orders.entity;

import com.develhope.spring.vehicle.entity.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Order {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name= "caution")
    private BigDecimal caution;
    @Column(nullable = false, name= "payed")
    private boolean payed;
    @Column(nullable = false, name = "status")
    private OrderStatus status;
    @Column(nullable = false, name = "vehicle")
    private Vehicle vehicle;



}
