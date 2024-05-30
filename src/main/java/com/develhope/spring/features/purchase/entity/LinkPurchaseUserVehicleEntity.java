package com.develhope.spring.features.purchase.entity;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "link_user_vehicles_on_purchase_activities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class LinkPurchaseUserVehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private UserEntity userEntity;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    @NotNull
    private VehicleEntity vehicleEntity;

    @OneToOne
    @JoinColumn(name = "purchase_id")
    @NotNull
    private PurchaseEntity purchaseEntity;

    public LinkPurchaseUserVehicleEntity(UserEntity userEntity, VehicleEntity vehicleEntity, PurchaseEntity purchaseEntity) {
        this.userEntity = userEntity;
        this.vehicleEntity = vehicleEntity;
        this.purchaseEntity = purchaseEntity;
    }
}
