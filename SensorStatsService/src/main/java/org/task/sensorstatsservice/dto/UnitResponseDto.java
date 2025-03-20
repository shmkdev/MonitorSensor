package org.task.sensorstatsservice.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UnitResponseDto {
    UUID id;
    String name;
}
