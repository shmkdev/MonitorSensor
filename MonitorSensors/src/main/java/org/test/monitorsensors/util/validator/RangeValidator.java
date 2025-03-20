package org.test.monitorsensors.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.test.monitorsensors.dto.SensorRequestDto;

public class RangeValidator implements ConstraintValidator<RangeConstraint, SensorRequestDto> {

    @Override
    public boolean isValid(SensorRequestDto sensorRequestDto, ConstraintValidatorContext context) {
        return sensorRequestDto.getRange().getTo() > sensorRequestDto.getRange().getFrom();
    }
}
