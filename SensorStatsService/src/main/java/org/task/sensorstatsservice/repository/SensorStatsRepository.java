package org.task.sensorstatsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.task.sensorstatsservice.entity.SensorStats;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SensorStatsRepository extends JpaRepository<SensorStats, Long> {
    List<SensorStats> findByStatDateBetween(LocalDate startDate, LocalDate endDate);
}
