package org.test.monitorsensors.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.monitorsensors.dto.SensorRequestDto;
import org.test.monitorsensors.dto.SensorResponseDto;
import org.test.monitorsensors.entity.Range;
import org.test.monitorsensors.entity.Sensor;
import org.test.monitorsensors.entity.TypeSensor;
import org.test.monitorsensors.entity.UnitSensor;
import org.test.monitorsensors.mapper.SensorMapper;
import org.test.monitorsensors.repository.SensorRepository;
import org.test.monitorsensors.repository.TypeSensorRepository;
import org.test.monitorsensors.repository.UnitSensorRepository;
import org.test.monitorsensors.service.SensorService;
import org.test.monitorsensors.util.exception.DataNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {
    private final SensorRepository sensorRepository;
    private final TypeSensorRepository typeRepository;
    private final UnitSensorRepository unitRepository;
    private final SensorMapper sensorMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SensorResponseDto> findAll() {
        return sensorRepository.findAll().stream()
                .map(sensorMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SensorResponseDto> findByNameContainingAndModelContaining(String name, String model) {
        return sensorRepository.findByNameContainingAndModelContaining(name, model).stream()
                .map(sensorMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public SensorResponseDto findById(UUID id) {
        return sensorRepository.findById(id)
                .map(sensorMapper::toDto)
                .orElseThrow(() -> new DataNotFoundException("Sensor not found with id: " + id));
    }

    @Override
    @Transactional
    public SensorResponseDto create(SensorRequestDto sensorRequestDto) {
        return Optional.of(createSensor(sensorRequestDto))
                .map(sensorRepository::save)
                .map(sensorMapper::toDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public SensorResponseDto update(UUID id, SensorRequestDto sensorRequestDto) {
        return sensorRepository.findById(id)
                .map(sensor -> update(sensor, sensorRequestDto))
                .map(sensorMapper::toDto)
                .orElseThrow(() -> new DataNotFoundException("Sensor not found with id: " + id));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Sensor not found with id: " + id));

        sensorRepository.delete(sensor);
    }

    private Sensor createSensor(SensorRequestDto sensorRequestDto) {
        UnitSensor unit = null;

        TypeSensor type = typeRepository.findByName(sensorRequestDto.getType()).orElseThrow(() ->
                new DataNotFoundException("Type not found with id: " + sensorRequestDto.getType()));

        if (sensorRequestDto.getUnit() != null) {
            unit = unitRepository.findByName(sensorRequestDto.getUnit())
                    .orElse(null);
        }

        return Sensor.builder()
                .name(sensorRequestDto.getName())
                .model(sensorRequestDto.getModel())
                .range(new Range(sensorRequestDto.getRange().getFrom(), sensorRequestDto.getRange().getTo()))
                .type(type)
                .unit(unit)
                .location(sensorRequestDto.getLocation())
                .description(sensorRequestDto.getDescription())
                .build();
    }

    private Sensor update(Sensor sensor, SensorRequestDto sensorRequestDto) {
        if (!sensor.getType().getName().equals(sensorRequestDto.getType())) {
            TypeSensor type = typeRepository.findByName(sensorRequestDto.getType()).orElseThrow(() ->
                    new DataNotFoundException("Type not found with id: " + sensorRequestDto.getType()));

            sensor.setType(type);
        }

        if (sensorRequestDto.getUnit() != null
            && (sensor.getUnit() == null || !sensor.getUnit().getName().equals(sensorRequestDto.getUnit()))) {
            UnitSensor unit = unitRepository.findByName(sensorRequestDto.getUnit()).orElseThrow(() ->
                    new DataNotFoundException("Unit not found with id: " + sensorRequestDto.getUnit()));

            sensor.setUnit(unit);
        }

        if (sensorRequestDto.getUnit() == null) {
            sensor.setUnit(null);
        }

        sensor.setName(sensorRequestDto.getName());
        sensor.setModel(sensorRequestDto.getModel());
        sensor.setRange(new Range(sensorRequestDto.getRange().getFrom(), sensorRequestDto.getRange().getTo()));
        sensor.setLocation(sensorRequestDto.getLocation());
        sensor.setDescription(sensorRequestDto.getDescription());

        return sensor;
    }
}
