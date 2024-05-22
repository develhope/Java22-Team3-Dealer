package com.develhope.spring.features.purchase.repository;

import com.develhope.spring.features.purchase.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity,Long> {
}
