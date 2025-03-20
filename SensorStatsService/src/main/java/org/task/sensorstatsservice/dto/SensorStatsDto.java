package org.task.sensorstatsservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SensorStatsDto {

    private LocalDate statDate;
    private Integer totalSensors;
    private String type;
    private Integer countByType;
}
