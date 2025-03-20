package org.test.monitorsensors.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RangeDto {

    @Positive(message = "From value must be positive")
    private Integer from;

    @Positive(message = "To value must be positive")
    private Integer to;
}
