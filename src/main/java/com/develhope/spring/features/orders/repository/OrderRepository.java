package com.develhope.spring.features.orders.repository;

import com.develhope.spring.features.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
