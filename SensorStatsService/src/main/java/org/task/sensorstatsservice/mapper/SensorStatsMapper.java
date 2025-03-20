package org.task.sensorstatsservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.task.sensorstatsservice.dto.SensorStatsDto;
import org.task.sensorstatsservice.entity.SensorStats;

@Mapper(componentModel = "spring")
public interface SensorStatsMapper {
    SensorStatsDto toDto(SensorStats sensorStats);
}
