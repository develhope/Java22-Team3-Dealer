package com.develhope.spring.features.rent.repositories;

import com.develhope.spring.features.rent.entities.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<RentEntity, Long> {
}
