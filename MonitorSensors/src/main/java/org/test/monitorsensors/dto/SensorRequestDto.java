package org.test.monitorsensors.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.test.monitorsensors.entity.Range;
import org.test.monitorsensors.entity.TypeSensor;
import org.test.monitorsensors.entity.UnitSensor;
import org.test.monitorsensors.util.validator.RangeConstraint;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RangeConstraint
public class SensorRequestDto {
    @NotBlank
    @Size(min = 3, max = 30)
    private String name;
    @NotBlank
    @Size(max = 15)
    private String model;
    private RangeDto range;
    private String type;
    private String unit;
    @Size(max = 40)
    private String location;
    @Size(max = 200)
    private String description;
}
