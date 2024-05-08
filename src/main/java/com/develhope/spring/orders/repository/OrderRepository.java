package com.develhope.spring.orders.repository;

import com.develhope.spring.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
