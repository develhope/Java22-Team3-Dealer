package com.develhope.spring.features.purchase.entity;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "link user vehicles on purchase activities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LinkPurchaseUserVehicleEntity {
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
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchaseEntity;

    public LinkPurchaseUserVehicleEntity(UserEntity userEntity, VehicleEntity vehicleEntity, PurchaseEntity purchaseEntity) {
        this.userEntity = userEntity;
        this.vehicleEntity = vehicleEntity;
        this.purchaseEntity = purchaseEntity;
    }
}
