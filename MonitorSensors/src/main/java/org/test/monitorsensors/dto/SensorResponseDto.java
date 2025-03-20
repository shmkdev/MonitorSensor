package org.test.monitorsensors.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import org.test.monitorsensors.entity.Range;
import org.test.monitorsensors.entity.TypeSensor;
import org.test.monitorsensors.entity.UnitSensor;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class SensorResponseDto {
    UUID id;
    String name;
    String model;
    Range range;
    String type;
    String unit;
    String location;
    String description;
    LocalDate localDate;
}

