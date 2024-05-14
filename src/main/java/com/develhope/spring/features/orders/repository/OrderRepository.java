package com.develhope.spring.features.orders.repository;

import com.develhope.spring.features.orders.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
