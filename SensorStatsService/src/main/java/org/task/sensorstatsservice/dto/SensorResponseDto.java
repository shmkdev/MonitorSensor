package org.task.sensorstatsservice.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class SensorResponseDto {
    UUID id;
    String name;
    String model;
    RangeDto range;
    String type;
    String unit;
    String location;
    String description;
    LocalDate createDate;
}

