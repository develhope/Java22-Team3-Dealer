package com.develhope.spring.features.purchase.repository;

import com.develhope.spring.features.purchase.entity.LinkPurchaseUserVehicleEntity;
import com.develhope.spring.features.purchase.entity.PurchaseEntity;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository
public interface LinkUserVehiclePurchaseRepository extends JpaRepository<LinkPurchaseUserVehicleEntity,Long> {
    List<LinkPurchaseUserVehicleEntity> findUserEntitiesByPurchaseEntity_Id(Long purchaseId);
    @Query(value = "SELECT v FROM Purchase p JOIN p.vehicle v WHERE p.purchaseDate BETWEEN :startDate AND :endDate GROUP BY v ORDER BY COUNT(v) DESC",nativeQuery = true)
    List<VehicleEntity> findTopSoldVehicleInPeriod( LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT v FROM Purchase p JOIN p.vehicle v ORDER BY v.price DESC", nativeQuery = true)
    List<VehicleEntity> findMostValuableSoldVehicle();

    @Query(value = "SELECT p FROM Purchase p WHERE p.user.id = :sellerId AND p.purchaseDate BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<PurchaseEntity> findSalesBySellerInPeriod( Long sellerId,  LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT SUM(p.amount) FROM Purchase p WHERE p.user.id = :sellerId AND p.purchaseDate BETWEEN :startDate AND :endDate",nativeQuery = true)
    Double findRevenueBySellerInPeriod( Long sellerId, LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT SUM(p.amount) FROM Purchase p WHERE p.purchaseDate BETWEEN :startDate AND :endDate",nativeQuery = true)
    Double findTotalRevenueInPeriod( LocalDateTime startDate,  LocalDateTime endDate);

    @Query(value = "SELECT * FROM `link user vehicles on purchase details` luvord Join purchase p on p.id = luvord.purchase_id ", nativeQuery = true)
    Optional<LinkPurchaseUserVehicleEntity> findByPurchase_Id(Long purchaseId);

    @Query(value = "SELECT * FROM `link user vehicles on purchase details` luvord Join vehicle_entity ve on ve.vehicle_id = luvord.vehicle_id ", nativeQuery = true)
    Optional<LinkPurchaseUserVehicleEntity> findByVehicle_Id(Long vehicleId);

    @Query(value = "SELECT * FROM `link user vehicles on purchases details` luvord Join user_entity ue on ue.user_id = luvord.user_id ", nativeQuery = true)
    Collection<LinkPurchaseUserVehicleEntity> findByUser_Id(Long UserId);
}
