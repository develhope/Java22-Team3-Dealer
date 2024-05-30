package com.develhope.spring.features.statistics.entities;

import com.develhope.spring.features.orders.entity.OrderEntity;
import com.develhope.spring.features.purchase.entity.PurchaseEntity;
import com.develhope.spring.features.rent.entities.RentEntity;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicleEntity;

    @OneToOne
    @JoinColumn(name = "rent_id")
    private RentEntity rentEntity;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @OneToOne
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchaseEntity;

    public StatisticsEntity(UserEntity userEntity, VehicleEntity vehicleEntity, RentEntity rentEntity, OrderEntity orderEntity, PurchaseEntity purchaseEntity) {
        this.userEntity = userEntity;
        this.vehicleEntity = vehicleEntity;
        this.rentEntity = rentEntity;
        this.orderEntity = orderEntity;
        this.purchaseEntity = purchaseEntity;
    }
}
