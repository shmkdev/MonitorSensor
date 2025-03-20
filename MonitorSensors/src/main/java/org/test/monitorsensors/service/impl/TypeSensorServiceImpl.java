package org.test.monitorsensors.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.monitorsensors.dto.TypeRequestDto;
import org.test.monitorsensors.dto.TypeResponseDto;
import org.test.monitorsensors.entity.TypeSensor;
import org.test.monitorsensors.mapper.TypeSensorMapper;
import org.test.monitorsensors.repository.TypeSensorRepository;
import org.test.monitorsensors.service.TypeSensorService;
import org.test.monitorsensors.util.exception.DataNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TypeSensorServiceImpl implements TypeSensorService {
    private final TypeSensorRepository typeSensorRepository;
    private final TypeSensorMapper typeSensorMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TypeResponseDto> findAll() {
        return typeSensorRepository.findAll().stream()
                .map(typeSensorMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TypeResponseDto findById(UUID id) {
        return typeSensorRepository.findById(id)
                .map(typeSensorMapper::toDto)
                .orElseThrow(() -> new DataNotFoundException("Type not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public TypeResponseDto findByName(String name) {
        return typeSensorRepository.findByName(name)
                .map(typeSensorMapper::toDto)
                .orElseThrow(() -> new DataNotFoundException("Type not found with name: " + name));
    }

    @Override
    @Transactional
    public TypeResponseDto create(TypeRequestDto typeRequestDto) {
        return Optional.of(typeRequestDto)
                .map(typeSensorMapper::toEntity)
                .map(typeSensorRepository::save)
                .map(typeSensorMapper::toDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public TypeResponseDto update(UUID id, TypeRequestDto typeRequestDto) {
        return typeSensorRepository.findById(id)
                .map(type -> typeSensorMapper.toUpdate(type, typeRequestDto))
                .map(typeSensorRepository::saveAndFlush)
                .map(typeSensorMapper::toDto)
                .orElseThrow(() -> new DataNotFoundException("Type not found with id: " + id));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        TypeSensor type = typeSensorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Type not found with id: " + id));

        typeSensorRepository.delete(type);
    }
}
