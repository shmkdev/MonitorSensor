package org.test.monitorsensors.service;

import org.test.monitorsensors.dto.SensorRequestDto;
import org.test.monitorsensors.dto.SensorResponseDto;

import java.util.List;
import java.util.UUID;

public interface SensorService {
    List<SensorResponseDto> findAll();
    List<SensorResponseDto> findByNameContainingAndModelContaining(String name, String model);
    SensorResponseDto findById(UUID id);
    SensorResponseDto create(SensorRequestDto sensorRequestDto);
    SensorResponseDto update(UUID id, SensorRequestDto sensorRequestDto);
    void delete(UUID id);
}
