package com.develhope.spring.features.purchase.repository;

import com.develhope.spring.features.purchase.entity.LinkPurchaseUserVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface LinkUserVehiclePurchRepository extends JpaRepository<LinkPurchaseUserVehicleEntity,Long> {
    List<LinkPurchaseUserVehicleEntity> findUserEntitiesByPurchaseEntity_Id(Long Id);

    Optional<LinkPurchaseUserVehicleEntity> findByPurchase_Id(Long Id);
    Optional<LinkPurchaseUserVehicleEntity> findByVehicle_Id(Long Id);
    Optional<LinkPurchaseUserVehicleEntity> findByUser_Id(Long Id);
}
