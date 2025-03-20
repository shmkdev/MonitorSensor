package org.test.monitorsensors.service;

import org.test.monitorsensors.dto.TypeRequestDto;
import org.test.monitorsensors.dto.TypeResponseDto;

import java.util.List;
import java.util.UUID;

public interface TypeSensorService {
    List<TypeResponseDto> findAll();
    TypeResponseDto findById(UUID id);
    TypeResponseDto findByName(String name);
    TypeResponseDto create(TypeRequestDto typeCreateDto);
    TypeResponseDto update(UUID id, TypeRequestDto typeCreateDto);
    void delete(UUID id);
}
