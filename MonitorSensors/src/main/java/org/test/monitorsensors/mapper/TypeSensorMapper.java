package org.test.monitorsensors.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.test.monitorsensors.dto.TypeRequestDto;
import org.test.monitorsensors.dto.TypeResponseDto;
import org.test.monitorsensors.entity.TypeSensor;

@Mapper(componentModel = "spring")
public interface TypeSensorMapper {
    TypeResponseDto toDto(TypeSensor type);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sensors", ignore = true)
    TypeSensor toEntity(TypeRequestDto typeRequestDto);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sensors", ignore = true)
    TypeSensor toUpdate(@MappingTarget TypeSensor type, TypeRequestDto typeRequestDto);
}
