package com.develhope.spring.features.purchase.repository;

import com.develhope.spring.features.orders.entity.Order;
import com.develhope.spring.features.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
}
