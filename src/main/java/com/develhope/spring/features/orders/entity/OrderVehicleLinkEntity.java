package com.develhope.spring.features.orders.entity;


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
public class OrderVehicleLinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicleEntity;

    // Costruttore con entità
    public OrderVehicleLinkEntity(OrderEntity orderEntity, VehicleEntity vehicleEntity) {
        this.orderEntity = orderEntity;
        this.vehicleEntity = vehicleEntity;
    }


}


