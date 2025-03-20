package org.test.monitorsensors.service;

import org.test.monitorsensors.dto.UnitRequestDto;
import org.test.monitorsensors.dto.UnitResponseDto;

import java.util.List;
import java.util.UUID;

public interface UnitSensorService {
    List<UnitResponseDto> findAll();
    UnitResponseDto findById(UUID id);
    UnitResponseDto findByName(String name);
    UnitResponseDto create(UnitRequestDto unitCreateDto);
    UnitResponseDto update(UUID id, UnitRequestDto unitCreateDto);
    void delete(UUID id);
}
