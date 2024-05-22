package com.develhope.spring.features.orders.entity;


import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "order_vehicle_link")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkOrderUserVehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicleEntity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public LinkOrderUserVehicleEntity(OrderEntity orderEntity, VehicleEntity vehicleEntity, UserEntity userEntity) {

        this.orderEntity = orderEntity;
        this.vehicleEntity = vehicleEntity;
        this.userEntity = userEntity;
    }
}


