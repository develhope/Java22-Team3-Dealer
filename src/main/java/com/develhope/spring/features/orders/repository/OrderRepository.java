package com.develhope.spring.features.orders.repository;

import com.develhope.spring.features.orders.entity.LinkOrderUserVehicleEntity;
import com.develhope.spring.features.orders.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {


}
