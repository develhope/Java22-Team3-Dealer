package com.develhope.spring.features.purchase.repository;

import com.develhope.spring.features.purchase.entity.LinkPurchaseUserVehicleEntity;
import com.develhope.spring.features.rent.entities.LinkRentUserVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface LinkUserVehiclePurchRepository extends JpaRepository<LinkPurchaseUserVehicleEntity,Long> {
    List<LinkPurchaseUserVehicleEntity> findUserEntitiesByPurchaseEntity_Id(Long purchaseId);

//    @Query(value = "SELECT * FROM `link user vehicles on purchase details` luvord Join rental r on r.id = luvord.purchase_id ", nativeQuery = true)
//    Optional<LinkPurchaseUserVehicleEntity> findByPurchase_Id(Long purchaseId);
//
//    @Query(value = "SELECT * FROM `link user vehicles on purchase details` luvord Join vehicle_entity ve on ve.vehicle_id = luvord.vehicle_id ", nativeQuery = true)
//    Optional<LinkPurchaseUserVehicleEntity> findByVehicle_Id(Long vehicleId);
//
//    @Query(value = "SELECT * FROM `link user vehicles on purchases details` luvord Join user_entity ue on ue.user_id = luvord.user_id ", nativeQuery = true)
//    Collection<LinkPurchaseUserVehicleEntity> findByUser_Id(Long UserId);
}
