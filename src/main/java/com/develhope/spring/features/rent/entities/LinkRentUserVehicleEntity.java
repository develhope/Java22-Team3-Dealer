package com.develhope.spring.features.rent.entities;

import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "link user vehicles on rentals details")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LinkRentUserVehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicleEntity;

    @OneToOne
    @JoinColumn(name = "rent_id")
    private RentEntity rentEntity;

    public LinkRentUserVehicleEntity(UserEntity userEntity, VehicleEntity vehicleEntity, RentEntity rentEntity) {
        this.userEntity = userEntity;
        this.vehicleEntity = vehicleEntity;
        this.rentEntity = rentEntity;
    }
}
