package org.task.sensorstatsservice.service;

import org.task.sensorstatsservice.dto.SensorStatsDto;

import java.time.LocalDate;
import java.util.List;

public interface SensorStatsService {
    void collectAndSaveStats();

    List<SensorStatsDto> findAll();

    List<SensorStatsDto> getStatsByDateRange(LocalDate startDate, LocalDate endDate);
}
