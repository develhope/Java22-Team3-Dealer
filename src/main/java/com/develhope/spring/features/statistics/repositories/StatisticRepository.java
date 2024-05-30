package com.develhope.spring.features.statistics.repositories;

import com.develhope.spring.features.statistics.entities.StatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<Long, StatisticsEntity> {
}
