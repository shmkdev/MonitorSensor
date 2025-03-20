package org.test.monitorsensors.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.monitorsensors.dto.UnitRequestDto;
import org.test.monitorsensors.dto.UnitResponseDto;
import org.test.monitorsensors.entity.UnitSensor;
import org.test.monitorsensors.mapper.UnitSensorMapper;
import org.test.monitorsensors.repository.UnitSensorRepository;
import org.test.monitorsensors.service.UnitSensorService;
import org.test.monitorsensors.util.exception.DataNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UnitSensorServiceImpl implements UnitSensorService {
    private final UnitSensorRepository unitRepository;
    private final UnitSensorMapper unitSensorMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UnitResponseDto> findAll() {
        return unitRepository.findAll().stream()
                .map(unitSensorMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UnitResponseDto findById(UUID id) {
        return unitRepository.findById(id)
                .map(unitSensorMapper::toDto)
                .orElseThrow(() -> new DataNotFoundException("Unit not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public UnitResponseDto findByName(String name) {
        return unitRepository.findByName(name)
                .map(unitSensorMapper::toDto)
                .orElseThrow(() -> new DataNotFoundException("Unit not found with name: " + name));
    }

    @Override
    @Transactional
    public UnitResponseDto create(UnitRequestDto unitRequestDto) {
        return Optional.of(unitRequestDto)
                .map(unitSensorMapper::toEntity)
                .map(unitRepository::save)
                .map(unitSensorMapper::toDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public UnitResponseDto update(UUID id, UnitRequestDto unitRequestDto) {
        return unitRepository.findById(id)
                .map(type -> unitSensorMapper.toUpdate(type, unitRequestDto))
                .map(unitRepository::saveAndFlush)
                .map(unitSensorMapper::toDto)
                .orElseThrow(() -> new DataNotFoundException("Unit not found with id: " + id));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        UnitSensor unit = unitRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Unit not found with id: " + id));

        unitRepository.delete(unit);
    }
}
