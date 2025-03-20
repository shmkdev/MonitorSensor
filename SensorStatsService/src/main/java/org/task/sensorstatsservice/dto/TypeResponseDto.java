package org.task.sensorstatsservice.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class TypeResponseDto {
    UUID id;
    String name;
}
