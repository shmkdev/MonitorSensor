package org.test.monitorsensors.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.test.monitorsensors.dto.SensorResponseDto;
import org.test.monitorsensors.entity.Sensor;

@Mapper(componentModel = "spring")
public interface SensorMapper {
    @Mapping(source = "type.name", target = "type")
    @Mapping(source = "unit.name", target = "unit")
    SensorResponseDto toDto(Sensor sensor);
}

