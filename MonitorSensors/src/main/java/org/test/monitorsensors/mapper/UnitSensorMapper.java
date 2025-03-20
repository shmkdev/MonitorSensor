package org.test.monitorsensors.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.test.monitorsensors.dto.UnitRequestDto;
import org.test.monitorsensors.dto.UnitResponseDto;
import org.test.monitorsensors.entity.UnitSensor;

@Mapper(componentModel = "spring")
public interface UnitSensorMapper {
    UnitResponseDto toDto(UnitSensor unit);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sensors", ignore = true)
    UnitSensor toEntity(UnitRequestDto unitRequestDto);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sensors", ignore = true)
    UnitSensor toUpdate(@MappingTarget UnitSensor unit, UnitRequestDto unitRequestDto);
}
